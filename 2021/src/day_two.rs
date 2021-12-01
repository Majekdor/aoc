use std::fs;

pub fn run() {
    println!("---------\n Day Two\n---------");

    let contents = fs::read_to_string("src/day_one.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.to_string()).collect();

    // Part One
    let count = 0;


    println!("Part One: {}", count);

    // Part Two


    println!("Part Two: {}", count);
}