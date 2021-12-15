use std::collections::BinaryHeap;
use std::fs;

pub fn run() {
    println!("-----------\nDay Fifteen\n-----------");

    let contents = fs::read_to_string("inputs/day_fifteen.txt").expect("File not found!");

    // Part One
    let map = contents.lines().map(|line| line.chars()
        .map(|c| (c as u8 - b'0') as i32).collect::<Vec<i32>>()).collect::<Vec<Vec<i32>>>();

    let part_one = find_path(&map);

    assert_eq!(part_one, 456);
    println!("Part One: {}", part_one);

    // Part Two
    let bigger_map = (0..(5 * map.len()))
        .map(|x| (0..(5 * map[0].len()))
            .map(|y| {
                let x_level = (x / map.len()) as i32;
                let y_level = (y / map[0].len()) as i32;
                let cost = map[x % map.len()][y % map[0].len()] + x_level + y_level;
                if cost < 10 {
                    cost
                } else {
                    cost - 9
                }
            }).collect::<Vec<i32>>()
        ).collect::<Vec<Vec<i32>>>();

    let part_two = find_path(&bigger_map);

    assert_eq!(part_two, 2831);
    println!("Part Two: {}", part_two);
}

fn find_path(map: &Vec<Vec<i32>>) -> i32 {
    let end = (map.len() -1, map[0].len() - 1);
    let mut distance = vec![vec![i32::MAX; map[0].len()]; map.len()];
    let mut queue = BinaryHeap::new();
    queue.push((0, 0, 0));
    while let Some((cost, x, y)) = queue.pop() {
        if (x, y) == end {
            return -cost;
        }
        if -cost > distance[x][y] {
            continue;
        }
        let mut points: Vec<(usize, usize)> = Vec::new();
        points.push((x + 1, y));
        points.push((x, y + 1));
        if x != 0 {
            points.push((x - 1, y));
        }
        if y != 0 {
            points.push((x, y - 1));
        }
        for (x1, y1) in points {
            if map.get(x1).and_then(|row| row.get(y1)).is_none() {
                continue;
            }
            let next_cost = -cost + map[x1][y1];
            if next_cost < distance[x1][y1] {
                queue.push((-next_cost, x1, y1));
                distance[x1][y1] = next_cost;
            }
        }
    }
    panic!("Unable to find path!");
}
