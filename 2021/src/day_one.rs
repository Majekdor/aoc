use std::fs;

pub fn run() {
    println!("---------\n Day One\n---------");

    let contents = fs::read_to_string("inputs/day_one.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.to_string()).collect();

    // Part One
    let mut line = 1;
    let mut count = 0;
    while line < lines.len() {
        let this_line:i32 = lines[line].parse().unwrap();
        let prev_line:i32 = lines[line - 1].parse().unwrap();
        if this_line > prev_line {
            count += 1;
        }
        line += 1;
    }
    println!("Part One: {}", count);

    // Part Two
    line = 2;
    count = 0;
    let mut prev_sum = -1;
    while line < lines.len() {
        let sum = lines[line].parse::<i32>().unwrap() + lines[line-1]
            .parse::<i32>().unwrap() + lines[line-2].parse::<i32>().unwrap();
        if sum > prev_sum && prev_sum != -1 {
            count += 1;
        }
        prev_sum = sum;
        line += 1;
    }

    println!("Part Two: {}", count);
}
