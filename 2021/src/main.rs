use std::time::Instant;

fn main() {
    println!("Starting...\n");
    let start = Instant::now();
    let run = "five";
    match run {
        "one" => day_one::run(),
        "two" => day_two::run(),
        "three" => day_three::run(),
        "four" => day_four::run(),
        "five" => day_five::run(),
        _ => println!("Invalid day.")
    }
    println!("\n...done in {}ms", start.elapsed().as_millis());
}

mod day_one;
mod day_two;
mod day_three;
mod day_four;
mod day_five;
