(ns design 
  (:require
   [garden.stylesheet :refer [at-media]]))

(def colors
  {:background "url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAAAXNSR0IArs4c6QAAChJJREFUeF7t3EFOI0sQhGF752PATYabwE24CdyEuQkcgx1P70npxSf9KvRWtajZINvtrurujMyIyPRcPz8/b4+Pj98PDw+3r6+v79fX19vr6+v38/Pz7f39/f7+vP74+Lg9PT3d35/Xb29vt5eXl+95Peed8837l8vldrlc7t+v9ed88/0/f/7c/v79++3fOe7n5+d2vV7vn8/1zPtznlnP/c71zefzvXl/1p39z/k8zs/nuuc8Xv98Pn+vl/Nvqzvw7wP5L2InYiay5omKjHmSEzFz/LyeyJnzGmEixoj/7bqzX88v4kTOrOc6hbQ5fs4j8r1fdX9mPTOQyD0I2Qofl8vVnDqRPX+tEUaIETevJ+Lq9USGuXwizprjcb52nUKONc4MYYRby+b13AfvR9U+M4NIntcHIbshZCJynpCswFwrIub4iYBiabIzI82INtKKvYk0c/1cl/suFmgtqdrq9Yi02Zfs1eNkdwchuyGkWIORbqTJ8ycy5f0TYcVizMXF2kofFHuR968QXDpBJM/+vK7Zn/ptjpeNDWK9/wchuyLEJ1k5txStNUidYG2a4yv3yu48n0pZPj/nlc3M/ivCramV80WO7Mz9WQNF5Nzvg5DdEDIRII+vyDJHGpnldYms8s50BmR56hRriPs24j1/1TAzRh2n12atNTPI2jzvQchuCDGXlhdVNcLcOSzLSNAV1SEwUjzenOz3RU7xfz03dcu8rppQuk1nwtqjZ+f+5/oOQnZDiE/SmiJiKlL1vn7bt1hFuv0DI9o+hcgor6m8tqo5IssaY19j9qleE8nqvYOQXRGiJ2UuNTJXfQ4Vtzm5+hL2UaxdK3dWnaFCtj8i+/M+lFc373ufZIXVj6l+0kHIbgiRFZWCLde0dIBeTXk81UEzJ6t0y1mY961pell6b4WUWlfklD7z+zV7MPs9CNkNIUZoPUHZisiqaQrPX7mzvCqVv26y57PGVIfP3F6dRD23YoWyJ2uffZtywQ9CdkOIbKlcSHl7zSmpP8zdIqZYTbE0a0u5qisPqzwlWZL3p+bDBpmyLpFhzRXBByG7IUT2VLnPKRAjXWVqZMiKqhMnv1ch+z3Zkvu0w+e+atLQGrma56oOpW6veswaeRCyG0Lmya+mQkTERISsZ5UjCxm6vc4xWROMYNc18mddc33VOBEv+1S3qLtcr3SVGeEgZDeE2EFbTZvoFZlbZWOyoHJv9ZzKw7JPYiTb+TSHizz3Y8ZQh9Q+1SHqo9ln9frnvh2E7IYQFbi1wZwvImoG2BxuZMl+7GOYc9U9IsXzqSdEhp+vXN2KeBV33S/7IrLbg5DNkDHbuc6TkZ+bm/Vw1AdGmKzJXG1k2QcpD8opF/VNdfKsSSu9s6pppcs8r0rfTDDXM++fGrIZUu6/D6lZU1mHrKV0iIrUCP2tV1W1QLYnwmvf1kxZZbGg8tDKu1vVMGvx3MeDkN0QIvswp5kDVfSVE9Uj8vBVb7tYiMgoh8H+Rk0U6oUV4nUKamrFyF/dX/d5ELIbQuTXNcnok5dXm4utESs3tGZn1RvmZtmYncdS0CKr9Fc5GbPf6uGLvJqit3YfhOyGEPWGEWEkFVsyp1bOrSkMa5fuqt8rpW6N0YuSfflaXeY65RaX0i/WVjrnIGRXhMjPRYYRqlI3F8rPV2xIl1a2ozI2kvWy3J8zy+VEWBurz2E/ZJVZ7I/o9c33D0J2Q4iRNEgxd8rGVOxOcRgRNbc068y6RppsTde3WFXpIBFQrHI1h+b1rJBV0yynp74ZItzO/X8Dqj6HiHHuqGpLsRkjvqY+1AUi1nXLba6+y6r/Ukp8NbEpe/I6zEgi7dSQzRBzld04vVHzVeZun/x8z/Oves9z3tJH8vpBhrVg5TZXR3KuXyTrSblPPb+5bjOKOkYEH4TshhAjXbbjEzTyVcbFNlTisqv6raP6ofooFbFej2zQWlLX636d57KjWk6CyFTPHITshhDZkDlaxV1TFkZM6QMjvFhJrWOkyq6qfzP7E3FG6MqT8zyFMFmr96MQdBCyG0KMEHOoLMa+hTrFGmNfQIXvtIlTJcVKPK8dwZU3pj5Y1QBzv0ha/Q6kftklwg9CdkNIzT8Z+eX5GOE1/7SaVyqlXTldtuQMsZ5YzZHZ8TPSrY3qKHWLswKlw9zfcXs3Q8Z9clEdIWKMvJoctHboyooAI2fF60uJu19roqxRFiZCytPTwZDtrZwKa7NTOAchuyLEJ10sS71QPXNzaM0Ee5w99KpNpU90javTVx6Z+kJWJOKL/ZULbT/E+z7XdVjWZki5TqSrOMvzWSlQz1O960KOkVRu9KqnLpKsAdbOciTql1xOs+iZFasrtjn37SBkN4TUrG6xAD0Ze+fF/+t7spyayS0X2vVEpC6wzoOItpdeirx+ISbbrKl82eDs6yBkN4SYi+u1vyW0v+Fs78r3l1VZO0SKrMspGGuhLGvlqZUXNeu6/spVLufBjKTOOwjZDSHmYJ+sekN2US6mvF43dtVX8HgRZF9FxBVC1RPWSmuKNaH0SfVV7KnXrPG8fxCyG0LMvaueuordmmO/o+apCiHOPRU7qxrmfqwNsjAzgB3F6teoW1bellMwlXkOQnZDiH6+udnPVwrXCNcb050tT0ykmaNrglJdVDPI6g3Pbw2z1uggqOirhlYNmfMdhOyGECPUjljNS8mfi1/L4vTIZEfWDF3Vea3Cdx2RrrdUSLcvo94oJK36HeVGD/Jm3YOQ3RAii1gpdztwIqqmPQpBNS1iBM9r2cx8v3SJDkKxId1h2af9m3ISav7LTCT7PAjZDBn5vwGtWJY9dSOwPB4j3AiUfenKlitdkaxLqxdnzfG63M9qf/Z3qtdvP8kMc2rIZki5Grm6m3o75sLq8KmkrQnzuQrW3KquMJKdpCwXWXbnTPPsTyRVB9R1PZ+Ilk1VDToI2Q0hKtnK7cXP9X48rryuWkfEycKK1ancPU4X21pnZnBd9ZDsrhyJeb+cBWv2QchuCDFnVq41t5cStg9RNWGFEM+ju+r61bsuT2zlyVkry8U2A1Qt0RMsFngQsitCzKGyGTttKnIjuHraspbqWdupk6W4P5FeU/gi0958scZBiF6atUikirRVJ/QgZDeEyNvLc1nx7urgyUb83clE2P9VyivXV8WvQi8XuFiYHVWvWwSWkyArm/UOQnZDSE1z1OyttUG2Uh0xc6usRf1ijTLSV/pEZFbuNyOUE6GuKSfCGql3VYiaGnkQshtCik2Vi1tur0p4NY/kzK2R6fo1BW+O1jmojqTTJxXxdkY9zozgTIHI9vtmioOQ3RBiBNX0RE2fmFutKfV5KVcVek0g6hLbf7CGGOn1uexH/VU9da9HN7tqk/rpIGQ3hJjD9Ol90k6hyCoq55aSl7WJCPWPtUpF7/oerzL3/FVb9J6qty+b/K1+m+8dhGyGkH8A3bwgkPJo7zkAAAAASUVORK5CYII=\") center center repeat rgb(32, 32, 36)"
   :semi-background "#303034"
   :link "#c9ada7"})

(def styles
  [:body
   {:text-shadow "0px -1px 0px rgba(0,0,0,.95)"
    :background (:background colors)
    :box-sizing "border-box"
    :padding "0px"
    :margin "0px"}
   [:*
     {:font-family "Inter"
      :line-height "1.65"
      :letter-spacing "-0.28px"
      :box-sizing "border-box"
      :position "relative"
      :padding "0"
      :margin "0"}]
   [:h1
    {:font-weight "600"
     :color "#fff"
     :letter-spacing "1px"}]
   [:h2
    {:font-weight "400"
     :color "rgba(255,255,255,.5)"
     :letter-spacing "6px"
     :text-transform "uppercase"
     :font-size "11px"
     :margin-bottom "10px"}]
   [:a 
    {:color (:link colors)
     :text-decoration "none"
     :border-bottom (str "1px solid " (:link colors))
     :transition "all 0.1s ease-in-out"
     :padding-bottom "1px"}
    [:&:hover
     {:color "#fff"
      :border-bottom "1px solid #fff"}]]])
       
(def responsive-styles
  (at-media 
    {:all true :max-device-width "600px"}
    [:body
     {:padding "25px"}]))        
            
