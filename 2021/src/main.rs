use std::time::Instant;

mod day_one;
mod day_two;
mod day_three;
mod day_four;

fn main() {
    println!("Starting...\n");
    let start = Instant::now();
    let run = "four";
    match run {
        "one" => day_one::run(),
        "two" => day_two::run(),
        "three" => day_three::run(),
        "four" => day_four::run(),
        _ => println!("Invalid day.")
    }
    println!("\nDone in {}ms", start.elapsed().as_millis());
}
