use std::time::Instant;

fn main() {
    println!("Starting...\n");
    let start = Instant::now();
    let run = "fifteen";
    match run {
        "one" => day_one::run(),
        "two" => day_two::run(),
        "three" => day_three::run(),
        "four" => day_four::run(),
        "five" => day_five::run(),
        "six" => day_six::run(),
        "seven" => day_seven::run(),
        "eight" => day_eight::run(),
        "nine" => day_nine::run(),
        "ten" => day_ten::run(),
        "eleven" => day_eleven::run(),
        "twelve" => day_twelve::run(),
        "thirteen" => day_thirteen::run(),
        "fourteen" => day_fourteen::run(),
        "fifteen" => day_fifteen::run(),

        "all" => {
            day_one::run();
            day_two::run();
            day_three::run();
            day_four::run();
            day_five::run();
            day_six::run();
            day_seven::run();
            day_eight::run();
            day_nine::run();
            day_ten::run();
            day_eleven::run();
            day_twelve::run();
            day_thirteen::run();
            day_fourteen::run();
            day_fifteen::run();
        },
        _ => println!("Invalid day.")
    }
    println!("\n...done in {}ms", start.elapsed().as_millis());
}

mod day_one;
mod day_two;
mod day_three;
mod day_four;
mod day_five;
mod day_six;
mod day_seven;
mod day_eight;
mod day_nine;
mod day_ten;
mod day_eleven;
mod day_twelve;
mod day_thirteen;
mod day_fourteen;
mod day_fifteen;
