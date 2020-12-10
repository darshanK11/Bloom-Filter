import java.util.HashSet;
import java.util.Scanner;

class NDS_Project2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numElements, numBits, numHashes, numRemoves, numAdded, numSets, numElementsInSet, numFilters;

        System.out.println("------------------------------------------------------------");
        System.out.println("BLOOM FILTER :-");
        System.out.print("Enter the number of elements to be encoded: ");
        numElements = sc.nextInt();
        System.out.print("Enter the number of bits in the filter: ");
        numBits = sc.nextInt();
        System.out.print("Enter the number of hashes: ");
        numHashes = sc.nextInt();

        bloomFilter(numElements, numBits, numHashes);

        System.out.println("COUNTING BLOOM FILTER");
        System.out.print("Enter the number of elements to be encoded initially: ");
        numElements = sc.nextInt();
        System.out.print("Enter the number of elements to be removed: ");
        numRemoves = sc.nextInt();
        System.out.print("Enter the number of elements to be added: ");
        numAdded = sc.nextInt();
        System.out.print("Enter the number of bits in the filter: ");
        numBits = sc.nextInt();
        System.out.print("Enter the number of hashes: ");
        numHashes = sc.nextInt();

        countingBloomFilter(numElements, numRemoves, numAdded, numBits, numHashes);

        System.out.println("CODED BLOOM FILTER");
        System.out.print("Enter the number of sets: ");
        numSets = sc.nextInt();
        System.out.print("Enter the number of elements in each set: ");
        numElementsInSet = sc.nextInt();
        System.out.print("Enter the number of filters: ");
        numFilters = sc.nextInt();
        System.out.print("Enter the number of bits in each filter: ");
        numBits = sc.nextInt();
        System.out.print("Enter the number of hashes: ");
        numHashes = sc.nextInt();

        codedBloomFilter(numSets, numElementsInSet, numFilters, numBits, numHashes);

        sc.close();

    }

    public static void bloomFilter(int numElements, int numBits, int numHashes) {

        int[] hashArray = new int[numHashes + 1];
        for (int i = 1; i < hashArray.length; i++) {
            hashArray[i] = (int) (Math.random() * Integer.MAX_VALUE) + 1;
        }

        int[] bitArray = new int[numBits + 1];

        HashSet<Integer> setA = new HashSet<>();
        int numSetA = 0;

        for (int i = 1; i <= numElements; i++) {
            int elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
            while (setA.contains(elementId)) {
                elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
            }
            setA.add(elementId);

            numSetA++;
            for (int j = 1; j < hashArray.length; j++) {
                int bitIndex = ((elementId ^ hashArray[j]) % numBits) + 1;
                bitArray[bitIndex] = 1;
            }
        }

        HashSet<Integer> setB = new HashSet<>();
        int numSetB = 0;
        for (int i = 1; i <= numElements; i++) {
            int elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
            while (setB.contains(elementId)) {
                elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
            }
            setB.add(elementId);
            boolean all1FlagBloomFilter = true;
            for (int j = 1; j < hashArray.length; j++) {
                int bitIndex = ((elementId ^ hashArray[j]) % numBits) + 1;
                if (bitArray[bitIndex] == 0) {
                    all1FlagBloomFilter = false;
                    break;
                }
            }
            if (all1FlagBloomFilter) {
                numSetB++;
            }

        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Set A - Number of elements in the filter = " + numSetA);
        System.out.println("Set B - Number of elements in the filter = " + numSetB);
        System.out.println("------------------------------------------------------------");

    }

    public static void countingBloomFilter(int numElements, int numRemoves, int numAdded, int numBits, int numHashes) {

        int[] hashArray = new int[numHashes + 1];
        for (int i = 1; i < hashArray.length; i++) {
            hashArray[i] = (int) (Math.random() * Integer.MAX_VALUE) + 1;
        }

        int[] bitArray = new int[numBits + 1];

        HashSet<Integer> setA = new HashSet<>();

        for (int i = 1; i <= numElements; i++) {
            int elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
            while (setA.contains(elementId)) {
                elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
            }
            setA.add(elementId);

            for (int j = 1; j < hashArray.length; j++) {
                int bitIndex = ((elementId ^ hashArray[j]) % numBits) + 1;
                bitArray[bitIndex]++;
            }
        }

        for (int el : setA) {
            if (numRemoves == 0) {
                break;
            }
            for (int j = 1; j < hashArray.length; j++) {
                int bitIndex = ((el ^ hashArray[j]) % numBits) + 1;
                bitArray[bitIndex]--;
            }
            numRemoves--;
        }

        HashSet<Integer> setB = new HashSet<>();
        for (int i = 1; i <= numAdded; i++) {
            int elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
            while (setB.contains(elementId)) {
                elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
            }
            setB.add(elementId);
            for (int j = 1; j < hashArray.length; j++) {
                int bitIndex = ((elementId ^ hashArray[j]) % numBits) + 1;
                bitArray[bitIndex]++;
            }
        }

        int numSetB = 0;
        for (int el : setA) {
            boolean allNot0Flag = true;
            for (int j = 1; j < hashArray.length; j++) {
                int bitIndex = ((el ^ hashArray[j]) % numBits) + 1;
                if (bitArray[bitIndex] == 0) {
                    allNot0Flag = false;
                    break;
                }
            }
            if (allNot0Flag) {
                numSetB++;
            }
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Number of elements in the filter = " + numSetB);
        System.out.println("------------------------------------------------------------");

    }

    public static void codedBloomFilter(int numSets, int numElementsInSet, int numFilters, int numBits, int numHashes) {

        int[] hashArray = new int[numHashes + 1];
        for (int i = 1; i < hashArray.length; i++) {
            hashArray[i] = (int) (Math.random() * Integer.MAX_VALUE) + 1;
        }

        HashSet<Integer>[] setArray = new HashSet[numSets + 1];
        int[][] bitArray = new int[numFilters + 1][numBits + 1];

        int numLookup = 0;

        for (int i = 1; i <= numSets; i++) {
            setArray[i] = new HashSet<>();
        }

        for (int i = 1; i <= numSets; i++) {
            for (int j = 1; j <= numElementsInSet; j++) {
                int elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
                while (setArray[i].contains(elementId)) {
                    elementId = (int) (Math.random() * Integer.MAX_VALUE) + 1;
                }
                setArray[i].add(elementId);
            }
        }

        for (int i = 1; i <= numSets; i++) {
            String binary = getBinary(i, numFilters);
            for (int elementId : setArray[i]) {
                for (int j = 0; j < binary.length(); j++) {
                    if (binary.charAt(j) == '1') {
                        for (int k = 1; k <= numHashes; k++) {
                            int bitIndex = ((elementId ^ hashArray[k]) % numBits) + 1;
                            bitArray[j + 1][bitIndex] = 1;
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= numSets; i++) {
            String binary = getBinary(i, numFilters);
            for (int elementId : setArray[i]) {
                StringBuilder check = new StringBuilder();
                for (int j = 0; j < binary.length(); j++) {
                    boolean all1Flag = true;
                    for (int k = 1; k <= numHashes; k++) {
                        int bitIndex = ((elementId ^ hashArray[k]) % numBits) + 1;
                        if (bitArray[j + 1][bitIndex] == 0) {
                            all1Flag = false;
                            break;
                        }
                    }
                    if (all1Flag) {
                        check.append("1");
                    } else {
                        check.append("0");
                    }
                }
                if (check.toString().equals(binary)) {
                    numLookup++;
                }
            }
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Number of elements whose lookup results are correct = " + numLookup);
        System.out.println("------------------------------------------------------------");

    }

    public static String getBinary(int n, int numFilters) {
        StringBuilder binary = new StringBuilder("");
        while (n != 0) {
            if (n % 2 == 0) {
                binary.append("0");
            } else {
                binary.append("1");
            }
            n /= 2;
        }
        while (binary.length() < numFilters) {
            binary.append("0");
        }
        return binary.reverse().toString();
    }
}
