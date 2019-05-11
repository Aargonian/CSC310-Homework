def solveTowers(disks, pegA, pegB, pegC):
    if disks == 1:
        print('Move disk', disks, 'from',  pegA, 'to', pegC)
    else:
        solveTowers(disks-1, pegA, pegC, pegB);
        print('Move disk', disks, 'from',  pegA, 'to', pegC)
        solveTowers(disks-1, pegB, pegA, pegC);

def main():
    disks = int(input("How many disks: "))
    solveTowers(disks, 'A', 'B', 'C')

if __name__ == '__main__':
    main()