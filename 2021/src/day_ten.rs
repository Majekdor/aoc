use std::fs;

pub fn run() {
    println!("---------\n Day Ten\n---------");

    let contents = fs::read_to_string("inputs/day_ten.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.parse().unwrap()).collect();

    // Part One
    let mut count = 0;
    for line in &lines {
        let tuple = is_corrupt(&line);
        if tuple.0 {
            count += tuple.1;
        }
    }

    assert_eq!(count, 442131);
    println!("Part One: {}", count);

    // Part Two
    let no_corrupt_lines:Vec<String> = lines.iter().filter(|line| !is_corrupt(&line).0)
        .map(|s| s.parse().unwrap()).collect();

    let mut scores:Vec<i64> = vec![];
    for line in no_corrupt_lines {
        let mut score:i64 = 0;
        let mut open:Vec<char> = vec![];
        for current in line.chars() {
            if is_open(current) {
                open.push(current);
            } else {
                open.pop();
            }
        }
        open.reverse();
        for c in open {
            score *= 5;
            score += match c {
                '(' => 1,
                '[' => 2,
                '{' => 3,
                '<' => 4,
                _ => panic!("Unable to match!")
            }
        }
        scores.push(score);
    }

    scores.sort();
    let score = scores[scores.len() / 2];

    assert_eq!(score, 3646451424);
    println!("Part Two: {}", score);
}

fn is_open(char: char) -> bool {
    match char {
        '(' | '[' | '{' | '<' => true,
        _ => false
    }
}

fn is_corrupt(line: &String) -> (bool, i32) {
    let mut open:Vec<char> = vec![];
    for current in line.chars() {
        if is_open(current) {
            open.push(current);
        } else {
            let open_form = match current {
                ')' => '(',
                ']' => '[',
                '}' => '{',
                '>' => '<',
                _ => panic!("Unable to match!")
            };
            if open.pop().unwrap() != open_form {
                return (true, match current {
                    ')' => 3,
                    ']' => 57,
                    '}' => 1197,
                    '>' => 25137,
                    _ => panic!("Unable to match!")
                });
            }
        }
    }
    (false, -1)
}
