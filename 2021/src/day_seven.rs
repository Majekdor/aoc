use std::fs;

pub fn run() {
    println!("---------\nDay Seven\n---------");

    let contents = fs::read_to_string("inputs/day_seven.txt").expect("File not found!");
    let nums:Vec<i32> = contents.split(",").map(|s| s.parse().unwrap()).collect();

    // Part One
    let mut in_order = nums.to_owned();
    in_order.sort_unstable();

    let median = in_order[in_order.len() / 2];

    let count:i32 = nums.into_iter()
        .map(|pos| (pos - median).abs()).sum();

    assert_eq!(count, 342730);
    println!("Part One: {}", count);

    // Part Two
    let nums:Vec<i32> = contents.split(",").map(|s| s.parse().unwrap()).collect();

    let avg:i32 = nums.iter().sum::<i32>() / nums.len() as i32;

    let count = nums.iter().map(|num| {
        let distance = (avg - num).abs(); // get distance to avg
        distance * (distance + 1) / 2 // calculate fuel cost
    }).sum::<i32>();

    assert_eq!(count, 92335207);
    println!("Part Two: {}", count);
}
