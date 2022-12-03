import Foundation

public func day01() -> (Int, Int) {
    let contents = try! String(contentsOfFile: "Inputs/Day01.txt")
    let elves = contents.split(separator: "\n\n")
    var part1Max = 0
    var part2Maxes: [Int] = [0, 0, 0]

    for elf in elves {
        var calories = 0

        for line in elf.split(separator: "\n") {
            calories += Int(line) ?? 0
        }

        if calories > part1Max {
            part1Max = calories
        }

        var part2MinAndIndex = (Int.max, 0)

        for i in part2Maxes.indices {
            if part2Maxes[i] < part2MinAndIndex.0 {
                part2MinAndIndex.0 = part2Maxes[i]
                part2MinAndIndex.1 = i
            }
        }

        if calories > part2MinAndIndex.0 {
            part2Maxes[part2MinAndIndex.1] = calories
        }
    }

    let part2 = part2Maxes[0] + part2Maxes[1] + part2Maxes[2]

    return (part1Max, part2)
}
