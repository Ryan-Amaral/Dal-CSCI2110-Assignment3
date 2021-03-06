# Dal-CSCI2110-Assignment3
<h2>Assignment 3 for CSCI 2110 (Computer Science 3)</h2>
<p>This program shows a basic array list implementation and a quad tree implementation of an abstranct data type that stores 2D points, and has functions for returning how many of those points lie within a query rectangle. The array list implementation is best with inserting in terms of time, and the quad tree implementation is best for the count and query operations.</p>

<p>Of note is the files in the output folder. The dot files were generated by this program to be used by the program <a href="http://www.graphviz.org/">Graphviz</a> to generate a graph of the quad tree. The PDFs of a few graphs are also in that folder.</p>

<h2>Sample Output:</h2>
<h3>Time Comparison:</h3>
<pre>
Adding 100000 points to QuadTree took 92 nanoseconds.
Adding 100000 points to Simple took 8 nanoseconds.
Calling count 100000 times on QuadTree takes 13173 nanoseconds.
Calling count 100000 times on Simple takes 87299 nanoseconds.
</pre>

<h3>From QuadTreePlayground.java:</h3>
<pre>
For a 1000x1000 QuadTree and a 10x10 Finder Rectangle: 
The most points found by the finder at once is: 9.
The least points found by the finder at once is: 0.
The average points found by the finder at once is: 0.9946678910315274.
</pre>

<h3>Printing Structures (Better versions in output directory)</h3>
<pre>
Simple implementation (Array List): {(82,258), (28,134), (112,174), (169,271), (254,46), (87,298), (237,299), (381,250), (322,220), (117,355), (500,458), (198,480), (56,432), (94,114), (508,84), (395,288), (367,84), (461,43), (125,114), (461,361), (155,251), (72,148), (124,428), (194,476), (301,250), (117,461), (421,221), (127,119), (492,442), (96,257), (31,104), (138,263), (496,208), (369,34), (289,139), (222,446), (108,276), (140,498), (178,176), (271,407), (507,242), (493,496), (211,236), (94,346), (203,138), (196,76), (170,365), (406,123), (286,115), (231,11), (366,164), (470,230), (297,334), (258,187), (250,363), (32,157), (341,212), (384,23), (384,446), (508,252), (349,258), (494,448), (116,448), (329,345), (207,219), (435,203), (22,419), (366,413), (9,127), (361,242), (35,263), (350,483), (324,509), (231,460), (444,457), (469,434), (429,243), (159,447), (471,411), (427,438), (293,417), (393,229), (7,77), (176,385), (410,117), (303,166), (469,272), (2,328), (415,449), (29,310), (394,414), (311,22), (49,419), (328,185), (420,143), (82,368), (80,198), (422,71)}
Quad Tree implementation: 
P
   P
      P
         (461,43)
         (384,23)
         P
            (422,71)
            0
            P
               0
               0
               0
               P
                  (410,117)
                  0
                  (406,123)
                  0
            0
         (508,84)
      P
         (369,34)
         (311,22)
         (286,115)
         (367,84)
      P
         P
            0
            0
            (328,185)
            (366,164)
         P
            (289,139)
            0
            (258,187)
            (303,166)
         (301,250)
         P
            0
            P
               0
               0
               (322,220)
               (341,212)
            0
            P
               0
               0
               (361,242)
               (381,250)
      P
         0
         (420,143)
         P
            P
               (435,203)
               0
               (421,221)
               0
            0
            (393,229)
            (429,243)
         P
            (496,208)
            0
            (470,230)
            P
               0
               0
               0
               P
                  (507,242)
                  0
                  0
                  (508,252)
   P
      P
         P
            (231,11)
            0
            0
            (254,46)
         0
         0
         (196,76)
      P
         0
         0
         P
            0
            (7,77)
            P
               (31,104)
               0
               (9,127)
               0
            0
         P
            0
            0
            (94,114)
            P
               0
               0
               0
               P
                  P
                     (125,114)
                     0
                     0
                     (127,119)
                  0
                  0
                  0
      P
         P
            0
            (72,148)
            0
            (112,174)
         P
            (32,157)
            (28,134)
            0
            0
         0
         (80,198)
      P
         (203,138)
         (178,176)
         (155,251)
         P
            0
            (207,219)
            (211,236)
            0
   P
      P
         (237,299)
         P
            (169,271)
            (138,263)
            0
            0
         (170,365)
         (250,363)
      P
         P
            P
               0
               (96,257)
               (108,276)
               0
            (82,258)
            (87,298)
            0
         P
            (35,263)
            0
            (29,310)
            0
         (2,328)
         P
            0
            (94,346)
            (82,368)
            (117,355)
      P
         (124,428)
         P
            0
            0
            (22,419)
            P
               (49,419)
               0
               0
               (56,432)
         0
         P
            P
               P
                  0
                  (116,448)
                  (117,461)
                  0
               0
               0
               0
            0
            0
            0
      P
         (222,446)
         P
            (176,385)
            0
            (159,447)
            0
         (140,498)
         P
            (231,460)
            (194,476)
            (198,480)
            0
   P
      P
         (469,272)
         (395,288)
         0
         (461,361)
      P
         (349,258)
         0
         (297,334)
         (329,345)
      P
         (366,413)
         P
            0
            (271,407)
            0
            (293,417)
         0
         P
            0
            0
            P
               (350,483)
               0
               (324,509)
               0
            0
      P
         P
            0
            (471,411)
            (469,434)
            (492,442)
         P
            0
            (394,414)
            (384,446)
            (427,438)
         P
            (444,457)
            (415,449)
            0
            0
         P
            P
               (500,458)
               (494,448)
               0
               0
            0
            0
            (493,496)
</pre>