(ns simple.lazy)

(take 100 (reductions + 0 (cycle [1 1 -1])))
