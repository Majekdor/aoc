use std::collections::HashSet;
use std::fs;
use std::ops::Index;

pub fn run() {
    println!("----------\n Day Nine\n----------");

    let contents = fs::read_to_string("inputs/day_nine.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.parse().unwrap()).collect();

    const COLUMNS: usize = 100;
    const ROWS: usize = 100;

    let mut matrix:Matrix<ROWS,COLUMNS> = Matrix::new();
    for i in 0..ROWS {
        let line = lines.get(i).unwrap();
        for j in 0..COLUMNS {
            matrix.data[i][j] = (line.as_bytes()[j] as usize) - 48;
        }
    }

    // Part One
    let mut count = 0;
    for i in 0..ROWS {
        for j in 0..COLUMNS {
            if matrix.low_point(i, j) {
                count += 1 + matrix.data[i][j];
            }
        }
    }

    assert_eq!(count, 423);
    println!("Part One: {}", count);

    // Part Two
    let mut basin_sizes:Vec<usize> = vec![];
    let mut seen:HashSet<Point> = HashSet::new();
    for i in 0..ROWS {
        for j in 0..COLUMNS {
            if matrix.low_point(i, j) {
                basin_sizes.push(matrix.basin_size(Point{
                    row: i,
                    column: j
                }, &mut seen));
            }
        }
    }

    basin_sizes.sort();
    let count:usize = basin_sizes.iter().rev().take(3).product();

    assert_eq!(count, 1198704);
    println!("Part Two: {}", count);
}

#[derive(Debug, Clone, Eq, PartialEq, Hash)]
struct Point {
    row: usize,
    column: usize,
}

pub struct Matrix<const ROWS: usize, const COLS: usize> {
    data: [[usize; COLS]; ROWS],
}

impl<const ROWS: usize, const COLS: usize> Matrix<ROWS, COLS> {
    pub fn new() -> Self {
        Self { data: [[0; COLS]; ROWS] }
    }

    fn num_above_greater(&mut self, row: usize, column: usize) -> bool {
        return if row == 0 {
            true
        } else {
            if self.data[row - 1][column] > self.data[row][column] {
                true
            } else {
                false
            }
        }
    }

    fn num_below_greater(&mut self, row: usize, column: usize) -> bool {
        return if row == 99 {
            true
        } else {
            if self.data[row + 1][column] > self.data[row][column] {
                true
            } else {
                false
            }
        }
    }

    fn num_right_greater(&mut self, row: usize, column: usize) -> bool {
        return if column == 99 {
            true
        } else {
            if self.data[row][column + 1] > self.data[row][column] {
                true
            } else {
                false
            }
        }
    }

    fn num_left_greater(&mut self, row: usize, column: usize) -> bool {
        return if column == 0 {
            true
        } else {
            if self.data[row][column - 1] > self.data[row][column] {
                true
            } else {
                false
            }
        }
    }

    fn low_point(&mut self, row: usize, column: usize) -> bool {
        return self.num_below_greater(row, column) && self.num_above_greater(row, column)
            && self.num_left_greater(row, column) && self.num_right_greater(row, column);
    }

    fn neighbors(&mut self, row: usize, column: usize) -> Vec<Point> {
        let mut neighbors:Vec<Point> = vec![];
        if row != 0 {
            neighbors.push(Point {
                row: row - 1,
                column
            });
        }
        if row != 99 {
            neighbors.push(Point {
                row: row + 1,
                column
            });
        }
        if column != 0 {
            neighbors.push(Point {
                row,
                column: column - 1
            });
        }
        if column != 99 {
            neighbors.push(Point {
                row,
                column: column + 1
            });
        }
        neighbors
    }

    fn basin_size(&mut self, point: Point, seen: &mut HashSet<Point>) -> usize {
        if seen.contains(&point) {
            return 0;
        }

        if self.data[point.row][point.column] == 9 {
            return 0;
        }

        let row = point.row;
        let column = point.column;
        seen.insert(point);

        let mut total = 1;

        for neighbor in self.neighbors(row, column) {
            total += self.basin_size(neighbor, seen);
        }
        total
    }
}

impl<const ROWS: usize, const COLS: usize> Index<(usize, usize)> for Matrix<ROWS, COLS> {
    type Output = usize;

    fn index(&self, index: (usize, usize)) -> &Self::Output {
        &self.data[index.0][index.1]
    }
}
