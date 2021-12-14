use std::collections::HashSet;
use std::fs;

pub fn run() {
    println!("------------\nDay Thirteen\n------------");

    let contents = fs::read_to_string("inputs/day_thirteen.txt").expect("File not found!");
    let points_str: &str = contents.split("\n\n").next().unwrap();
    let folds_str: &str = contents.split("\n\n").last().unwrap();

    let mut points: Vec<Point> = Vec::new();
    for line in points_str.lines() {
        points.push(Point {
            x: line.split(",").next().unwrap().parse::<usize>().unwrap(),
            y: line.split(",").last().unwrap().parse::<usize>().unwrap(),
        });
    }

    let mut folds: Vec<Fold> = Vec::new();
    for line in folds_str.lines() {
        folds.push(Fold {
            axis: line.strip_prefix("fold along ").unwrap().split("=").next().unwrap().parse::<char>().unwrap(),
            loc: line.strip_prefix("fold along ").unwrap().split("=").last().unwrap().parse::<usize>().unwrap(),
        });
    }

    // Part One
    let part_one = fold(points.clone(), folds[0]).len();

    assert_eq!(part_one, 607);
    println!("Part One: {}", part_one);

    // Part Two
    let mut points_clone = points.clone();
    for f in folds {
        points_clone = fold(points_clone, f);
    }

    let mut part_two = String::new();
    for y in 0..=points_clone.iter().max_by(|l, r| l.y.cmp(&r.y)).unwrap().y {
        part_two.push('\n');
        for x in 0..=points_clone.iter().max_by(|l, r| l.x.cmp(&r.x)).unwrap().x {
            if points_clone.contains(&Point { x, y, }) {
                part_two.push('#');
            } else {
                part_two.push(' ');
            }
        }
    }

    assert_eq!(part_two, "\n ##  ###  #### #    ###  #### #### #   \n#  # #  #    # #    #  # #       # #   \n#    #  #   #  #    #  # ###    #  #   \n#    ###   #   #    ###  #     #   #   \n#  # #    #    #    #    #    #    #   \n ##  #    #### #### #    #    #### ####");
    println!("Part Two: {}", part_two);
}

fn fold(points: Vec<Point>, fold: Fold) -> Vec<Point> {
    let mut points_clone = points.clone();
    if fold.axis == 'x' {
        let dots = points_clone.iter().filter(|p| p.x > fold.loc).copied().collect::<Vec<_>>();
        points_clone = points_clone.into_iter().filter(|p| p.x < fold.loc).collect();
        for dot in dots.iter() {
            points_clone.push(Point {
                x: fold.loc - (dot.x - fold.loc),
                y: dot.y,
            });
        }
    } else if fold.axis == 'y' {
        let dots = points_clone.iter().filter(|p| p.y > fold.loc).copied().collect::<Vec<_>>();
        points_clone = points_clone.into_iter().filter(|p| p.y < fold.loc).collect();
        for dot in dots.iter() {
            points_clone.push(Point {
                x: dot.x,
                y: fold.loc - (dot.y - fold.loc),
            });
        }
    }
    points_clone.into_iter()
        .collect::<HashSet<_>>()
        .into_iter()
        .collect()
}

#[derive(Debug, Clone, Copy, Eq, PartialEq, Hash)]
struct Point {
    x: usize,
    y: usize,
}

#[derive(Debug, Clone, Copy, Eq, PartialEq, Hash)]
struct Fold {
    axis: char,
    loc: usize,
}
