(ns simple.regexp)

; http://d.hatena.ne.jp/minazoko/20100531/1275309344

(re-seq #"[0-9a-f]+" "123abc 345 fffa")

(re-seq #"(0x)?([0-9a-f]+)(h)?" "0x123abc 345h 0xfffa")
