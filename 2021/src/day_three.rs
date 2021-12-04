use std::fs;

pub fn run() {
    println!("---------\nDay Three\n---------");

    let contents = fs::read_to_string("inputs/day_three.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.to_string()).collect();

    // Part One
    let mut gamma:String = "".to_owned();
    let mut epsilon:String = "".to_owned();
    for i in 0..12 {
        let num_ones = lines.iter().filter(|str| str.chars().nth(i).unwrap() == '1').count();
        if num_ones >= lines.len() / 2 {
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
    for i in 0..12 {
        if oxygen_generator_rating.len() != 1 {
            let num_ones = oxygen_generator_rating.iter().filter(|str| str.chars().nth(i).unwrap() == '1').count();
            let num_zeroes = oxygen_generator_rating.iter().filter(|str| str.chars().nth(i).unwrap() == '0').count();
            if num_ones >= num_zeroes {
                oxygen_generator_rating.retain(|str| str.chars().nth(i).unwrap() == '1');
            } else {
                oxygen_generator_rating.retain(|str| str.chars().nth(i).unwrap() == '0');
            }
        }
        if co2_scrubber_rating.len() != 1 {
            let num_ones = co2_scrubber_rating.iter().filter(|str| str.chars().nth(i).unwrap() == '1').count();
            let num_zeroes = co2_scrubber_rating.iter().filter(|str| str.chars().nth(i).unwrap() == '0').count();
            if num_ones < num_zeroes {
                co2_scrubber_rating.retain(|str| str.chars().nth(i).unwrap() == '1');
            } else {
                co2_scrubber_rating.retain(|str| str.chars().nth(i).unwrap() == '0');
            }
        }
    }

    println!("Part Two: {}", i64::from_str_radix(&oxygen_generator_rating.get(0).unwrap(), 2).unwrap()
        * i64::from_str_radix(&co2_scrubber_rating.get(0).unwrap(), 2).unwrap());
}
