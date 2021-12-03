use std::fs;

pub fn run() {
    println!("----------\n Day Four\n----------");

    let contents = fs::read_to_string("src/day_four.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.to_string()).collect();

    // Part One


    println!("Part One: {}", "N/A");

    // Part Two


    println!("Part Two: {}", "N/A");
}