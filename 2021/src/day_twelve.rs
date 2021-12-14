use std::collections::{HashMap, HashSet};
use std::fs;

pub fn run() {
    println!("----------\nDay Twelve\n----------");

    let contents = fs::read_to_string("inputs/day_twelve.txt").expect("File not found!");
    let mut path = HashMap::new();
    for line in contents.lines() {
        let (a,b) = line.split_once('-').unwrap();
        path.entry(a).or_insert(Vec::new()).push(b);
        path.entry(b).or_insert(Vec::new()).push(a);
    }

    // Part One
    let part_one = part_one(&path, "start", &mut HashSet::new());

    assert_eq!(part_one, 5076);
    println!("Part One: {}", part_one);

    // Part Two
    let part_two = part_two(&path, "start", &mut HashSet::new(), None);

    assert_eq!(part_two, 145643);
    println!("Part Two: {}", part_two);
}

// Life times are weird...
fn part_one<'a>(path: &HashMap<&'a str, Vec<&'a str>>, location: &'a str, seen: &mut HashSet<&'a str>) -> usize {
    if location == "end" {
        return 1;
    }

    if location.chars().all(|c| c.is_lowercase()) && !seen.insert(location) {
        return 0;
    }

    let count = path[location].iter()
        .map(|n| part_one(path, n, seen))
        .sum::<usize>();
    seen.remove(location);

    count
}

fn part_two<'a>(path: &HashMap<&'a str, Vec<&'a str>>, location: &'a str,
                seen: &mut HashSet<&'a str>, mut seen_twice: Option<&'a str>) -> usize {
    if location == "end" {
        return 1;
    }

    if location.chars().all(|c| c.is_lowercase()) && !seen.insert(location) {
        if seen_twice.is_some() || location == "start" {
            return 0;
        }
        seen_twice = Some(location);
    }

    let count = path[location].iter()
        .map(|n| part_two(path, n, seen, seen_twice))
        .sum::<usize>();

    if seen_twice.unwrap_or("") != location {
        seen.remove(location);
    }

    count
}
