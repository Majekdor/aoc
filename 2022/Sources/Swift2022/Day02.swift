//
//  File.swift
//  
//
//  Created by Kevin Barnes on 12/3/22.
//

import Foundation

public func day02() -> (Int, Int) {
    let contents = try! String(contentsOfFile: "Inputs/Day02.txt")
    let lines = contents.split(separator: "\n")
    var part1 = 0
    var part2 = 0
    
    for line in lines {
        let parts = line.split(separator: " ")
        let firstColumn = parts[0]
        let secondColumn = parts[1]
        
        switch secondColumn {
        case "X":
            part1 += 1
            
            switch firstColumn {
            case "A":
                part1 += 3
            case "B":
                part1 += 0
            case "C":
                part1 += 6
            default:
                part1 += 0
            }
        case "Y":
            part1 += 2
            
            switch firstColumn {
            case "A":
                part1 += 6
            case "B":
                part1 += 3
            case "C":
                part1 += 0
            default:
                part1 += 0
            }
        case "Z":
            part1 += 3
            
            switch firstColumn {
            case "A":
                part1 += 0
            case "B":
                part1 += 6
            case "C":
                part1 += 3
            default:
                part1 += 0
            }
        default:
            part1 += 0
        }
    }
    
    return (part1, 0)
}
