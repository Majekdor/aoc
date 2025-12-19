//
//  Day02.swift
//  2025
//
//  Created by Kevin Barnes on 12/9/25.
//

import Foundation

struct Day02 {
    
    static let contents = try! String(contentsOfFile: "Inputs/Day02.txt")
    
    static var ranges: [ClosedRange<Int>] {
        contents.split(separator: ",")
            .filter { !$0.isEmpty }
            .map { range in
                let parts = range.split(separator: "-")
                let lowerBound = Int(parts[0].trimmingCharacters(in: .whitespacesAndNewlines))!
                let upperBound = Int(parts[1].trimmingCharacters(in: .whitespacesAndNewlines))!
                return lowerBound...upperBound
            }
    }
    
    static func part1() -> Int {
        var answer = 0
        
        for range in ranges {
            for number in range {
                let string = String(number)
                
                if string.count % 2 != 0 {
                    continue
                }
                
                let first = string.prefix(string.count / 2)
                let second = string.suffix(string.count / 2)
                
                if first == second {
                    answer += number
                }
            }
        }
        
        return answer
    }
    
    static func part2() -> Int {
        var matches: Set<Int> = []
        
        for range in ranges {
            for number in range {
                let chars = Array(String(number))
                
                if chars.count == 1 {
                    continue
                }
                
                var count = chars.count
                if (count % 2 != 0) {
                    count += 1
                }
                
                for i in 1...(count / 2) {
                    if chars.count % i != 0 {
                        continue
                    }
                    
                    let chunks = stride(from: 0, through: chars.count, by: i).map {
                        chars[$0 ..< min($0 + i, chars.count)]
                    }
                    
                    var allMatch = true
                    var prev: ArraySlice<String.Element>? = nil
                    for chunk in chunks {
                        if chunk.isEmpty {
                            continue
                        }
                        
                        if prev == nil {
                            prev = chunk
                            continue
                        }
                        
                        if prev != chunk {
                            allMatch = false
                            break
                        }
                        
                        prev = chunk
                    }
                    
                    if allMatch {
                        matches.insert(number)
                    }
                }
            }
        }
        
        return matches.reduce(0, +)
    }
}
