use std::fs;

pub fn run() {
    println!("----------\n Day Five\n----------");

    let contents = fs::read_to_string("inputs/day_five.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.to_string()).collect();

    // Part One
    let clouds = process_lines(&lines, false);
    let count = count_overlapping(&clouds);

    assert_eq!(count, 8111);
    println!("Part One: {}", count);

    // Part Two
    let clouds = process_lines(&lines, true);
    let count = count_overlapping(&clouds);

    assert_eq!(count, 22088);
    println!("Part Two: {}", count);
}

fn count_overlapping(clouds: &Vec<Vec<i32>>) -> i32 {
    let mut count = 0;
    for i in 0..1000 {
        for j in 0..1000 {
            if clouds[i as usize][j as usize] >= 2 {
                count += 1;
            }
        }
    }
    count
}

fn process_lines(lines: &Vec<String>, part_two: bool) -> Vec<Vec<i32>> {
    let mut clouds = vec![vec![0; 1000]; 1000];
    for line in lines {
        let to_coords:Vec<String> = line.split(" -> ").map(|s| s.to_string()).collect();
        let to = Point::new(to_coords.get(0).unwrap());
        let from = Point::new(to_coords.get(1).unwrap());

        // Vertical lines
        if to.x == from.x {
            if to.y > from.y {
                for i in from.y..to.y+1 {
                    clouds[i as usize][to.x as usize] += 1;
                }
            } else {
                for i in to.y..from.y+1 {
                    clouds[i as usize][to.x as usize] += 1;
                }
            }
        }

        // Horizontal lines
        else if to.y == from.y {
            if to.x > from.x {
                for i in from.x..to.x+1 {
                    clouds[to.y as usize][i as usize] += 1;
                }
            } else {
                for i in to.x..from.x+1 {
                    clouds[to.y as usize][i as usize] += 1;
                }
            }
        }

        // Diagonal lines - Part two
        else if part_two {
            let mut smaller_x = if to.x > from.x {from.x} else {to.x};
            let bigger_x = if to.x < from.x {from.x} else {to.x};
            let mut smaller_y = if to.y > from.y {from.y} else {to.y};
            let mut bigger_y = if to.y < from.y {from.y} else {to.y};
            let slope = (to.y - from.y) / (to.x - from.x);
            if slope > 0 {
                while smaller_x != bigger_x+1 && smaller_y != bigger_y+1 {
                    clouds[smaller_y as usize][smaller_x as usize] += 1;
                    smaller_x += 1;
                    smaller_y += 1;
                }
            } else {
                while smaller_x != bigger_x+1 && bigger_y != smaller_y-1 {
                    clouds[bigger_y as usize][smaller_x as usize] += 1;
                    smaller_x += 1;
                    bigger_y -= 1;
                }
            }
        }
    }
    clouds
}

struct Point {
    x: i32,
    y: i32,
}

impl Point {
    fn new(point: &String) -> Self {
        let coords:Vec<String> = point.split(",").map(|s| s.to_string()).collect();
        Self {
            x: i32::from_str_radix(&coords.get(0).unwrap(), 10).unwrap(),
            y: i32::from_str_radix(&coords.get(1).unwrap(), 10).unwrap()
        }
    }
}
