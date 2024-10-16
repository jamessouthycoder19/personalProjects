// swift-tools-version: 6.0
import PackageDescription

let package = Package(
    name: "ticTacToe",
    dependencies: [
        // Dependencies declare other packages that this package depends on.
    ],
    targets: [
        .target(
            name: "ticTacToe",
            dependencies: []),
        .testTarget(
            name: "ticTacToeTests",
            dependencies: ["MySwiftProject"]),
    ]
)
