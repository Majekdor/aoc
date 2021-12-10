use std::collections::HashSet;
use std::fs;

pub fn run() {
    println!("---------\nDay Eight\n---------");

    let contents = fs::read_to_string("inputs/day_eight.txt").expect("File not found!");
    let lines:Vec<String> = contents.split("\n").map(|s| s.parse().unwrap()).collect();

    // Part One
    let mut count = 0;
    for line in &lines {
        let parts:Vec<String> = line.split(" | ").map(|s| s.parse().unwrap()).collect();
        let output_strings:Vec<String> = parts[1].split(" ").map(|s| s.parse().unwrap()).collect();
        for string in output_strings {
            if string.len() == 2 || string.len() == 3 || string.len() == 4 || string.len() == 7 {
                count += 1;
            }
        }
    }

    assert_eq!(count, 530);
    println!("Part One: {}", count);

    // Part Two
    let mut count = 0;
    for line in lines {
        let patterns = line.split(" | ").collect::<Vec<_>>();

        let mut mapping: [HashSet<char>; 10] = Default::default();

        for pat in patterns[0].split(" ") {
            match pat.len() {
                2 => mapping[1] = pat.chars().into_iter().collect(),
                3 => mapping[7] = pat.chars().into_iter().collect(),
                4 => mapping[4] = pat.chars().into_iter().collect(),
                7 => mapping[8] = pat.chars().into_iter().collect(),
                _ => (),
            }
        }

        for pat in patterns[0].split(" ") {
            if pat.len() == 5 {
                let tmp: HashSet<char> = pat.chars().into_iter().collect();

                if mapping[1].difference(&tmp).count() == 0 {
                    mapping[3] = tmp;
                } else if tmp.difference(&mapping[4]).count() == 2 {
                    mapping[5] = tmp;
                } else if tmp.difference(&mapping[4]).count() == 3 {
                    mapping[2] = tmp;
                }
            }
        }

        for pat in patterns[0].split(" ") {
            if pat.len() == 6 {
                let tmp: HashSet<char> = pat.chars().into_iter().collect();

                if mapping[1].difference(&tmp).count() == 1 {
                    mapping[6] = tmp;
                } else if mapping[5].difference(&tmp).count() == 0 {
                    mapping[9] = tmp;
                } else {
                    mapping[0] = tmp;
                }
            }
        }

        let mut num = 0;
        for (i, pat) in patterns[1]
            .split(" ")
            .collect::<Vec<_>>()
            .iter()
            .rev()
            .enumerate()
        {
            let tmp: HashSet<char> = pat.chars().into_iter().collect();
            num += mapping.iter().position(|x| x == &tmp).unwrap() as i32 * i32::pow(10, i as u32);
        }
        count += num;
    }

    assert_eq!(count, 1051087);
    println!("Part Two: {}", count);
}
