any problem which can be designed using trees/recursion
can be solved using - Composite Design Pattern

composite - object inside object


File System (a directory can contain a file or another directory)
-----------
|
| Directory
  \| Directory
     \| Directory
     \| File
  \| File
| File

UML (Directory has a File System)


                               --------------------------------------
                              |                                      |   
                         File System                                 |
               is-a         |   |        is-a                        |
         -------------------     -----------------------             |
        |                                               |     has-a  |
        File                                       Directory --------