use std::fs;

pub fn run() {
    println!("----------\n Day Four\n----------");

    let contents = fs::read_to_string("inputs/day_four.txt").expect("File not found!");
    let mut sections:Vec<String> = contents.split("\n\n").map(|s| s.to_string()).collect();
    let first_line = sections[0].clone();
    let drawn_numbers:Vec<i32> = first_line.split(",").map(|c| i32::from_str_radix(c, 10).unwrap()).collect();
    sections.remove(0);

    // Part One
    let mut part_one_boards = get_boards(&sections);
    let part_one = part_one(&drawn_numbers, &mut part_one_boards);
    assert_eq!(part_one, 58838); // make sure it stays correct in my refactoring

    println!("Part One: {}", part_one);

    // Part Two
    let mut part_two_boards = get_boards(&sections);
    let part_two = part_two(&drawn_numbers, &mut part_two_boards);
    assert_eq!(part_two, 6256); // make sure it stays correct in my refactoring

    println!("Part Two: {}", part_two);
}

fn part_one(drawn_numbers: &Vec<i32>, boards: &mut Vec<BingoBoard>) -> i32 {
    for drawn_number in drawn_numbers {
        for board in boards.iter_mut() {
            board.score_board(&drawn_number);
            board.check_won();
            if board.has_won {
                return get_final_score(board, drawn_number);
            }
        }
    }
    -1
}

fn part_two(drawn_numbers: &Vec<i32>, boards: &mut Vec<BingoBoard>) -> i32 {
    for drawn_number in drawn_numbers {
        for board in boards.iter_mut() {
            board.score_board(&drawn_number);
            board.check_won();
        }
        let mut non_winning_boards:Vec<BingoBoard> = boards.clone();
        non_winning_boards.retain(|b| !b.has_won);
        if non_winning_boards.len() == 1 {
            let mut board = non_winning_boards.get(0).unwrap().to_owned();
            return final_board(&drawn_numbers, &mut board);
        }
    }
    -1
}

fn final_board(drawn_numbers: &Vec<i32>, board: &mut BingoBoard) -> i32 {
    for drawn_number in drawn_numbers {
        board.score_board(drawn_number);
        board.check_won();
        if board.has_won {
            return get_final_score(board, drawn_number);
        }
    }
    -1
}

fn get_final_score(board: &BingoBoard, drawn_number: &i32) -> i32 {
    let mut unmarked: i32 = 0;
    for row in &board.rows {
        row.iter().filter(|n| !n.drawn).for_each(|n| unmarked += n.value);
    }
    return drawn_number * unmarked;
}

fn get_boards(sections: &Vec<String>) -> Vec<BingoBoard> {
    let mut boards:Vec<BingoBoard> = vec![];
    for i in 0..sections.len() {
        let lines:Vec<String> = sections.get(i).unwrap().split("\n").map(|s| s.to_string()).collect();
        let mut rows:Vec<Vec<Number>> = vec![];
        for j in 0..lines.len() {
            let line = lines.get(j).unwrap();
            let nums:Vec<i32> = line.split_whitespace().map(|s| i32::from_str_radix(s, 10).unwrap()).collect();
            let mut numbers:Vec<Number> = vec![];
            for num in nums {
                numbers.push(Number {value: num, drawn: false});
            }
            rows.push(numbers);
        }
        boards.push(BingoBoard {
            rows,
            has_won: false,
        });
    }
    boards
}

#[derive(Clone, Copy)]
struct Number {
    value: i32,
    drawn: bool,
}

#[derive(Clone)]
struct BingoBoard {
    rows: Vec<Vec<Number>>,
    has_won: bool,
}

impl BingoBoard {
    fn row(&self, row: usize) -> &Vec<Number> {
        return &self.rows[row];
    }
    fn check_won(&mut self) {
        // Check for 5 across
        for row in &self.rows {
            if row.iter().filter(|n| n.drawn).count() == 5 {
                self.has_won = true;
            }
        }
        // Check for 5 down
        for i in 0..5 {
            if self.row(0).get(i).unwrap().drawn
                && self.row(1).get(i).unwrap().drawn
                && self.row(2).get(i).unwrap().drawn
                && self.row(3).get(i).unwrap().drawn
                && self.row(4).get(i).unwrap().drawn
            {
                self.has_won = true;
            }
        }
    }
    fn score_board(&mut self, drawn_number: &i32) {
        for row in self.rows.iter_mut() {
            for number in row.iter_mut() {
                if number.value == *drawn_number {
                    number.drawn = true;
                }
            }
        }
    }
}