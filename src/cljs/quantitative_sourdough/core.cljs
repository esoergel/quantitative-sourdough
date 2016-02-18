(ns quantitative-sourdough.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(comment
  (run)
  (browser-repl)
  (ns quantitative-sourdough.core)
  )

(def default-recipe
  {:title "Basic Sourdough"
   :ingredients
   [{:name "White Flour"
     :type :flour
     :preferment 200
     :mix 500}
    {:name "Wheat Flour"
     :type :flour
     :preferment 100
     :mix 100}
    {:name "Rye Flour"
     :type :flour
     :preferment 100}
    {:name "Water"
     :type :liquid
     :preferment 250
     :mix 500}
    {:name "Sea Salt"
     :type :salt
     :mix 21}
    {:name "Sourdough starter"
     :type :leavening
     :preferment 50}]})

(enable-console-print!)

(defonce app-state (atom {:title "Basic Sourdough"
                          :recipe default-recipe}))

(def table-headers
  ["Ingredient"
   "Preferment"
   "Mix"
   "Total"
   "Percentage"])

(defn ingredient-row [{name :name preferment :preferment mix :mix}]
  (dom/tr nil
    (dom/td nil name)
    (dom/td nil preferment)
    (dom/td nil mix)
    (dom/td nil (+ preferment mix))
    (dom/td nil)
   ))

(defn recipe-table [recipe]
  (dom/table nil
             (dom/thead nil (apply dom/tr nil
                                    (for [heading table-headers]
                                      (dom/th nil heading))))
             (apply dom/tbody nil
                    (map ingredient-row (:ingredients recipe)))))

(defn root-component [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil (dom/h1 nil (:title app))
               (recipe-table (:recipe app))))))

(om/root
 root-component
 app-state
 {:target (. js/document (getElementById "app"))})
