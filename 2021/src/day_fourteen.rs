use std::collections::HashMap;
use std::fs;
use itertools::Itertools;

pub fn run() {
    println!("------------\nDay Fourteen\n------------");

    let contents = fs::read_to_string("inputs/day_fourteen.txt").expect("File not found!");
    let start = contents.split("\n\n").next().unwrap();
    let rules = contents.split("\n\n").last().unwrap();

    let rule_map = rules.lines()
        .map(|line| {
            let (ingredients, result) = line.split_once(" -> ").unwrap();
            let ingredient_tuple = (ingredients.chars().next().unwrap(),
                                    ingredients.chars().skip(1).next().unwrap());
            (ingredient_tuple, result.chars().next().unwrap())
        })
        .collect::<HashMap<(char,char),char>>();

    // Part One
    let part_one = most_sub_least(&rule_map, start, 10);

    assert_eq!(part_one, 2740);
    println!("Part One: {}", part_one);

    // Part Two
    let part_two = most_sub_least(&rule_map, start, 40);

    assert_eq!(part_two, 2959788056211);
    println!("Part Two: {}", part_two);
}

fn most_sub_least(rules: &HashMap<(char,char),char>, start: &str, steps: usize) -> usize {
    let count_start = start.chars().tuple_windows().counts();
    let count_pairs = (0..steps).fold(count_start, |counts, _| {
        let mut count_next = HashMap::new();
        for (&(a, b), count) in &counts {
            let result = rules[&(a, b)];
            *count_next.entry((a, result)).or_insert(0) += count;
            *count_next.entry((result, b)).or_insert(0) += count;
        }
        count_next
    });
    let mut count = HashMap::new();
    for (&(a, _), c) in &count_pairs {
        *count.entry(a).or_insert(0) += c;
    }
    *count.entry(start.chars().last().unwrap()).or_insert(0) += 1;
    let max = count.values().max().unwrap();
    let min = count.values().min().unwrap();
    max - min
}