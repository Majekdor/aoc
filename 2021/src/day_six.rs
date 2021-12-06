use std::collections::VecDeque;
use std::fs;

pub fn run() {
    println!("---------\n Day Six\n---------");

    let contents = fs::read_to_string("inputs/day_six.txt").expect("File not found!");
    let mut nums:Vec<i32> = contents.split(",").map(|s| s.parse().unwrap()).collect();

    // Part One
    let mut part_one = fish_count(&mut nums, 80);

    assert_eq!(part_one, 375482);
    println!("Part One: {}", part_one);

    // Part Two
    let part_two = fish_count(&mut nums, 256);

    assert_eq!(part_two, 1689540415957);
    println!("Part Two: {}", part_two);
}

fn fish_count(nums: &mut Vec<i32>, days: i32) -> u64 {
    let mut fish:VecDeque<u64> = [0u64; 9].iter().copied().collect();

    for num in nums {
        fish[*num as usize] += 1;
    }

    for _ in 0..days {
        let old_zero = fish.pop_front().unwrap(); // get num 0s
        fish.push_back(old_zero); // push num 0s to back for num 8s
        fish[6] += old_zero; // add num 0s to num 6s for reset
    }
    fish.into_iter().sum::<u64>()
}
