(ns quantitative-sourdough.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [quantitative-sourdough.core-test]))

(enable-console-print!)

(doo-tests 'quantitative-sourdough.core-test)
