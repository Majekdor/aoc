use std::collections::HashMap;
use std::collections::HashSet;
use std::collections::VecDeque;
use std::fs;
use std::str::FromStr;

pub fn run() {
    println!("----------\nDay Eleven\n----------");

    let contents = fs::read_to_string("inputs/day_eleven.txt").expect("File not found!");
    let data:Data = contents.parse().unwrap();

    // Part One
    let part_one = data.clone().part_one();

    assert_eq!(part_one, 1644);
    println!("Part One: {}", part_one);

    // Part Two
    let part_two = data.part_two();

    assert_eq!(part_two, 229);
    println!("Part Two: {}", part_two);
}

#[derive(Debug, Clone)]
struct Data {
    map: HashMap<Point, usize>,
    rows: usize,
    columns: usize,
}

#[derive(Debug, Clone, Copy, Eq, PartialEq, Hash)]
struct Point {
    row: usize,
    column: usize,
}

impl Point {
    fn neighbors(&self, data: &Data) -> Vec<Point> {
        let offsets: [(i8, i8); 8] = [
            (0, 1),
            (0, -1),
            (1, 0),
            (1, 1),
            (1, -1),
            (-1, 0),
            (-1, 1),
            (-1, -1),
        ];

        let mut neighbors = Vec::new();

        for (row_offset, col_offset) in offsets {
            let row = (self.row as isize + row_offset as isize) as usize;
            let column = (self.column as isize + col_offset as isize) as usize;

            let point = Point { row, column, };
            if point.within_bounds(data.rows - 1, data.columns - 1) {
                neighbors.push(point);
            }
        }

        neighbors
    }

    fn within_bounds(&self, max_row: usize, max_col: usize) -> bool {
        self.row <= max_row && self.column <= max_col
    }
}

impl Data {
    fn part_one(&self) -> usize {
        let mut data = self.clone();
        (0..100).map(|_| data.step()).sum()
    }

    fn part_two(&self) -> usize {
        let mut data = self.clone();

        (1..).find_map(|idx| {
            data.step();
            if data.map.iter().all(|(_, energy)| *energy == 0) {
                Some(idx)
            } else {
                None
            }
        }).unwrap()
    }

    fn step(&mut self) -> usize {
        let mut flashes = 0;
        let mut queue: VecDeque<Point> = VecDeque::new();
        let mut visited: HashSet<Point> = HashSet::new();

        for (point, energy) in self.map.iter_mut() {
            *energy += 1;
            if *energy > 9 {
                queue.push_back(*point);
                visited.insert(*point);
            }
        }

        while let Some(point) = queue.pop_front() {
            let neighbors: Vec<Point> = point.neighbors(&self);
            for neighbor in neighbors {
                let energy = self.map.get_mut(&neighbor).unwrap();
                *energy += 1;
                if *energy > 9 && !visited.contains(&neighbor) {
                    queue.push_back(neighbor);
                    visited.insert(neighbor);
                }
            }
        }

        for (_, energy) in self.map.iter_mut() {
            if *energy > 9 {
                flashes += 1;
                *energy = 0;
            }
        }

        flashes
    }
}

// This is really cool
impl FromStr for Data {
    type Err = ();

    fn from_str(input: &str) -> Result<Self, Self::Err> {
        let input = input.trim();
        let rows = input.lines().count();
        let columns = input.lines().nth(0).unwrap().len();

        let map = input.lines().enumerate().flat_map(|(row, line)| {
                line.chars().enumerate().map(move |(column, c)| {
                    let num = c.to_digit(10).unwrap() as usize;
                    let point = Point { row, column, };
                    (point, num)
                })
            }).collect();

        Ok(Self { map, rows, columns })
    }
}
