use std::time::Instant;

mod day_one;
mod day_two;
mod day_three;

use std::fs;

fn main() {
    println!("Starting...\n");
    let start = Instant::now();
    let run = "three";
    match run {
        "one" => day_one::run(),
        "two" => day_two::run(),
        "three" => day_three::run(),
        _ => println!("Invalid day.")
    }
    println!("\nDone in {}ms", start.elapsed().as_millis());
}
