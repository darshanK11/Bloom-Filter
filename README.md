# Bloom-Filter

In this project, I have implemented 3 types of Bloom Filters:
1. Bloom Filter
2. Counting Bloom Filter
3. Coded Bloom Filter

## How to Run

Run the code using the following command:
```bash
javac NDS_Project2.java
java NDS_Project2
```

Now, the code will ask for the following details:
1. For the Bloom Filter
  1. Number of elements to be encoded
  2. Number of bits in the filter
  3. Number of hashes
2. For the Counting Bloom Filter
  1. Number of elements to be encoded initially
  2. Number of elements to be removed
  3. Number of elements to be added
  4. Number of bits in teh filter
  5. Number oh hashes
3. For the Coded Bloom Filter
  1. Number of Sets
  2. Number of elements in each set
  3. Number of filters
  4. Number of nits in each filter
  5. Number of hashes

After entering the details for the Bloom Filter, the output is shows in the terminal. Similary, after entering the details for the other 2 filters, the output is shown in the terminal.

The output is structured as follows:
1. For the Bloom Filter
  The first line is the number of elements found in the filter for Set A
  The second line will be the number of elements found in the filter for Set B
2. For the Counting Bloom Filter
  The number of elements in the filter after removing and adding elements is shown
3. For the Coded Bloom Filyer
  The number of elements whose lookup results are correct is shown
