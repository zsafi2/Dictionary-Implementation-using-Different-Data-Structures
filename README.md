### **Dictionary Implementation using Different Data Structures**
#### *A Java-based dictionary implementation using Binary Search Tree and HashMap.*

---

## **Table of Contents**
- [Description](#description)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Modules](#modules)
- [Technologies Used](#technologies-used)
- [Future Improvements](#future-improvements)
- [Contributing](#contributing)
- [License](#license)

---

## **Description**
This project implements a **dictionary** data structure in Java using multiple underlying data structures: **Binary Search Tree (BST)** and **HashMap**. The dictionary supports fundamental operations such as inserting, searching, and deleting key-value pairs. The implementations follow a unified interface to allow seamless switching between different data structures.

---

## **Features**
- **Binary Search Tree Dictionary:** Stores key-value pairs using a BST for efficient ordered storage and retrieval.
- **HashMap Dictionary:** Implements a dictionary with a hash table to provide fast lookups and insertions.
- **Queue Implementation:** Supports basic queue operations used in the BST iterator.
- **Common Interface (`ProjOneDictionary<K, V>`):** Ensures consistency across different dictionary implementations.
- **Exception Handling:** Detects and handles null value insertions gracefully.

---

## **Installation**
### **Prerequisites**
Ensure you have the following installed:
- **Java Development Kit (JDK)** (Version 8 or later)
- **Java Compiler (javac)**

### **Clone the Repository**
```bash
git clone https://github.com/yourusername/dictionary-structures.git
cd dictionary-structures
```

### **Compile the Code**
```bash
javac *.java
```

---

## **Usage**
Run the program using:
```bash
java ProjOneDictionary
```
The dictionary allows users to insert, search, and delete key-value pairs using different underlying data structures.

---

## **Modules**
| Module | Description |
|--------|------------|
| `ProjOneDictionary.java` | Interface defining dictionary operations. |
| `BinarySearchTreeDict.java` | Dictionary implementation using a Binary Search Tree (BST). |
| `HashMapDict.java` | Dictionary implementation using a HashMap. |
| `Queue.java` | Defines a generic queue interface. |
| `ListQueue.java` | Implements a queue using a linked list. |

---

## **Technologies Used**
- **Java**: Object-oriented programming language.
- **Binary Search Tree**: Provides efficient ordered storage and retrieval.
- **HashMap**: Enables fast key-value lookups.
- **Queue (ListQueue.java)**: Supports iterative traversal in BST.

---

## **Future Improvements**
- Implement additional data structures such as **AVL Tree** and **Red-Black Tree**.
- Improve the **hashing mechanism** for better distribution.
- Add a **graphical user interface (GUI)** for easier interaction.

---

## **Contributing**
Contributions are welcome! If you’d like to improve this project:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Add feature"`).
4. Push to the branch (`git push origin feature-name`).
5. Open a Pull Request.

---

## **License**
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

