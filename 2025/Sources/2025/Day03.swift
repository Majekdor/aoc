//
//  Day03.swift
//  2025
//
//  Created by Kevin Barnes on 12/19/25.
//

import Foundation

struct Day03 {
    
    static let contents = try! String(contentsOfFile: "Inputs/Day03.txt")
    
    static var banks: Array<Array<Int>> {
        var banks: Array<Array<Int>> = []
        let lines = contents.split(separator: "\n")
        for line in lines {
            var bank: Array<Int> = []
            for char in line {
                bank.append(Int(String(char))!)
            }
            banks.append(bank)
        }
        return banks
    }
    
    static func part1() -> Int {
        var total = 0
        
        for bank in banks {
            var tensPlaceIndex: Int = 0
            for i in 0..<(bank.count - 1) { // The tens place number can't be the last battery in the bank
                if bank[i] > bank[tensPlaceIndex] {
                    tensPlaceIndex = i
                }
            }
            
            var onesPlace: Int = 0
            for i in (tensPlaceIndex + 1)..<bank.count {
                if bank[i] > onesPlace {
                    onesPlace = bank[i]
                }
            }
            
            let joltage = (bank[tensPlaceIndex] * 10) + onesPlace
            total += joltage
        }
        
        return total
    }
    
    static func part2() -> Int {
        var total = 0
        
        let exponents = [11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        
        for bank in banks {
            var joltage = 0
            
            var previousIndex = -1
            for exponent in exponents {
                var highest = 0
                for i in (previousIndex + 1)..<(bank.count - exponent) {
                    if bank[i] > highest {
                        highest = bank[i]
                        previousIndex = i
                    }
                }
                joltage += (highest * Int(pow(10.0, Double(exponent))))
            }
            
            total += joltage
        }
        
        return total
    }
}
