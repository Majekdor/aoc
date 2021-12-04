use std::fs;

pub fn run() {
    println!("---------\n Day Two\n---------");

    let contents = fs::read_to_string("inputs/day_two.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.to_string()).collect();

    // Part One
    let mut horizontal = 0;
    let mut depth = 0;
    for line in &lines {
        let parts:Vec<String> = line.split(" ").map(|s| s.to_string()).collect();
        let command:String = parts[0].parse().unwrap();
        match command.as_ref() {
            "forward" => horizontal += parts[1].parse::<i32>().unwrap(),
            "down" => depth += parts[1].parse::<i32>().unwrap(),
            "up" => depth -= parts[1].parse::<i32>().unwrap(),
            _ => println!("Invalid command!")
        }
    }

    println!("Part One: {}", horizontal * depth);

    // Part Two
    horizontal = 0;
    depth = 0;
    let mut aim = 0;
    for line in &lines {
        let parts:Vec<String> = line.split(" ").map(|s| s.to_string()).collect();
        let command:String = parts[0].parse().unwrap();
        let num:i32 = parts[1].parse().unwrap();
        match command.as_ref() {
            "forward" => {
                horizontal += num;
                depth += aim * num;
            },
            "down" => aim += num,
            "up" => aim -= num,
            _ => println!("Invalid command!")
        }
    }

    println!("Part Two: {}", horizontal * depth);
}
