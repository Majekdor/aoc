import XCTest
@testable import Swift2022

final class Tests: XCTestCase {
    func testDay01() throws {
        let tuple = day01()
        XCTAssertEqual(tuple.0, 72478)
        XCTAssertEqual(tuple.1, 210367)
    }
    
    func testDay02() throws {
        let tuple = day02()
        XCTAssertEqual(tuple.0, 11841)
    }
}
