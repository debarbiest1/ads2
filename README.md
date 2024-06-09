<h1 align="center">Crunch01 <a href="https://platform.alem.school/git/kzhalmag/crunch01/src/branch/main/main.go" target="_blank"> full code here</a></h1>

Crunch01 is a Go program designed to print a map based on user input. The program prompts the user for the size of the map, the content (using the digits 0, 1, 2, 3), and the characters to represent the player, wall, and award. 

## There is an input to print an expected result:
#### 8 8
#### 00000000
#### 01103010
#### 01101110
#### 01010100
#### 01111110
#### 00010010
#### 02111010
#### 00000000
#### >
#### X
#### @

## Key Functions
### main(): Orchestrates the program flow, from receiving input to displaying the map.
### ExpandSlice(n, m int, inpSlice [][]int) [][]int: Expands the board for better visual representation.
### DrawBoard(grid [][]int, player, award, wall rune): Draws the board with appropriate formatting and colors.
### printHeader(m int, maxLenInt int): Prints the horizontal notation.
### printGrid(grid [][]int, m int, maxLenInt int, red, white, blue, yellow, resetColor string, player, award, wall rune): Prints the grid with colors and symbols.
### IntOrderToString(n int) string: Converts an integer to its corresponding alphabetical notation.
### clearScreen(): Clears the terminal screen.
### Print(s string, n int): Prints a string multiple times.
### PutNumber(n int): Prints an integer.


