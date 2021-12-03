use std::fs;

pub fn run() {
    println!("---------\nDay Three\n---------");

    let contents = fs::read_to_string("src/day_three.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.to_string()).collect();

    // Part One
    let mut gamma:String = "".to_owned();
    let mut epsilon:String = "".to_owned();
    for x in 0..12 {
        let mut ones = 0;
        let mut zeros = 0;
        for line in &lines {
            let chars:Vec<_> = line.chars().collect();
            if chars[x] == '0' {
                zeros += 1;
            } else {
                ones += 1;
            }
        }
        if ones > zeros {
            gamma.push_str("1");
            epsilon.push_str("0");
        } else {
            gamma.push_str("0");
            epsilon.push_str("1");
        }
    }

    println!("Part One: {}", i64::from_str_radix(&gamma, 2).unwrap() * i64::from_str_radix(&epsilon, 2).unwrap());

    // Part Two
    let mut oxygen_generator_rating = lines.clone();
    let mut co2_scrubber_rating = lines.clone();
    for x in 0..12 {
        let mut oxygen_ones = 0;
        let mut oxygen_zeroes = 0;
        let mut co2_ones = 0;
        let mut co2_zeroes = 0;
        for line in &oxygen_generator_rating {
            let chars:Vec<_> = line.chars().collect();
            if chars[x] == '0' {
                oxygen_zeroes += 1;
            } else {
                oxygen_ones += 1;
            }
        }
        for line in &co2_scrubber_rating {
            let chars:Vec<_> = line.chars().collect();
            if chars[x] == '0' {
                co2_zeroes += 1;
            } else {
                co2_ones += 1;
            }
        }
        if oxygen_generator_rating.len() != 1 {
            if oxygen_ones >= oxygen_zeroes {
                oxygen_generator_rating.retain(|str| str.chars().nth(x).unwrap() == '1');
            } else {
                oxygen_generator_rating.retain(|str| str.chars().nth(x).unwrap() == '0');
            }
        }
        if co2_scrubber_rating.len() != 1 {
            if co2_zeroes <= co2_ones {
                co2_scrubber_rating.retain(|str| str.chars().nth(x).unwrap() == '0');
            } else {
                co2_scrubber_rating.retain(|str| str.chars().nth(x).unwrap() == '1');
            }
        }
    }

    println!("Part Two: {}", i64::from_str_radix(&oxygen_generator_rating.get(0).unwrap(), 2).unwrap()
        * i64::from_str_radix(&co2_scrubber_rating.get(0).unwrap(), 2).unwrap());
}