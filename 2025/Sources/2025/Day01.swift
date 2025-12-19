//
//  Day01.swift
//  2025
//
//  Created by Kevin Barnes on 12/9/25.
//

import Foundation

struct Day01 {
    
    static let contents = try! String(contentsOfFile: "Inputs/Day01.txt")
    
    static var rotations: [Int] {
        contents.split(separator: "\n").map { rotation in
            let direction = Array(rotation)[0] == "R" ? 1 : -1
            let value = Int(String(rotation.dropFirst())) ?? 0
            return value * direction
        }
    }
    
    static func part1() -> Int {
        var value = 50
        var answer = 0
        
        for rotation in rotations {
            value += rotation
            if value % 100 == 0 {
                answer += 1
            }
        }
        
        return answer
    }
    
    static func part2() -> Int {
        var value = 50
        var answer = 0
        
        for rotation in rotations {
            for _ in 0..<abs(rotation) {
                value += rotation > 0 ? 1 : -1
                if value % 100 == 0 {
                    answer += 1
                }
            }
        }
        
        return answer
    }
}
