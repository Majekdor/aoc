import Foundation

@main
public struct Main {
    
    public static func main() {
        let start = Date()
        print(Day02.part2())
        print("Completed in \(Date().timeIntervalSince(start))s")
    }
}
