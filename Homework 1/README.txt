All programs are written to run with any version of Python 3.2 or greater and were tested working with a Python 3.7 interpreter.

The programs can be made executable in any (ba)sh process with chmod, or can be run by directly feeding them to the Python interpreter.

All programs are self-contained and do not require any special arguments unless it explicitly takes command line arguments. None currently do.

To pass the test data in the data folder to each program, simply call the program as normal and use the '<' syntax of the local shell along
with the path to the data file to input the data.

	Example:
		aargonian@LocalDev:~$ python3 src/hamming.py < data/hamming_test.dat
