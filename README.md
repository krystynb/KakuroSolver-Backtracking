# KakuroSolver-Backtracking
Solves a Kakuro Puzzle using Backtracking 

Kakuro puzzles are similar to crosswords, but use digits (from 1 to 9) instead of letters.

Rules :
- All empty cells need to be filled in with digits, in such a way that all the given sums are respected.
- You are not allowed to use the same digit more than once to obtain a given sum.
- 
Notation :
- 'X' : represents a cell that you don't need to fill.
- Empty cell : represents a cell that you need to fill with a digit (1 - 9).
- Cell with digit : the given digit is part of the solution, don't change it.
- Cell with backslash : the required sum of the corresponding cells.
- X\ : the vertical sum X of the cells downwards,
- \X : the horizontal sum X of the cells to the right,
- X\Y : the vertical sum X of the cells downwards, and the horizontal sum Y of the cells to the right.


Example 1:

height = 3, width = 3

| X | 9\ | 11\ |

| \17 | | |

| \3 | | |

For the horizontal sum to be 3 in the second row, we have 2 options: (2, 1) and (1, 2).

For the vertical sum to be 11 in the second column, we have 8 options: 
(9, 2), (2, 9), (8, 3), (3, 8), (7, 4), (4, 7), (6, 5) or (5, 6).

If we combine those options we find that the value in the bottom right cell has to be 2.

| X | 9\ | 11\ |

| \17 | | |

| \3 | | 2 |

Since 2 is now fixed, we can deduce the value of its neighbors : 11 - 2 = 9, and 3 - 2 = 1.

| X | 9\ | 11\ |

| \17 | | 9 |

| \3 | 1 | 2 |

Finally we can deduce the last value in the same way : 9 - 1 = 8, or 17 - 9 = 8.

So the solution is:

| X | 9\ | 11\ |

| \17 | 8 | 9 |

| \3 | 1 | 2 |


You can find here more information: https://en.wikipedia.org/wiki/Kakuro

Example 1

Input Specification: File kakuroInput.txt

3 3

X,9\,11\

\17,0,0

\3,0,0

Output Specification:

X,9\,11\

\17,8,9

\3,1,2

Example 2

Input Specification: File kakuroInput.txt

5 5

X,17\,6\,X,X

\9,8,0,24\,X

\20,0,0,0,4\

X,\14,0,0,0

X,X,\8,0,0

Output Specification:

X,17\,6\,X,X

\9,8,1,24\,X

\20,9,3,8,4\

X,\14,2,9,3

X,X,\8,7,1


Example 3

Input Specification: File kakuroInput.txt

9 8

X,17\,13\,X,X,X,X,X

\9,0,0,32\,X,27\,10\,X

\15,0,0,0,\9,2,0,X

X,\16,0,0,14\4,0,0,X

X,X,\12,0,0,0,X,X

X,X,11\24,0,0,0,8\,X

X,\6,0,0,\5,0,0,13\

X,\17,0,0,\16,0,0,0

X,X,X,X,X,\12,0,0

Output Specification:

X,17\,13\,X,X,X,X,X

\9,8,1,32\,X,27\,10\,X

\15,9,5,1,\9,2,7,X

X,\16,7,9,14\4,1,3,X

X,X,\12,3,5,4,X,X

X,X,11\24,7,9,8,8\,X

X,\6,2,4,\5,3,2,13\
X,\17,9,8,\16,9,1,6
X,X,X,X,X,\12,5,7
