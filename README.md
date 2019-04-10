Discount Service Evaluation Kata

Pillar Technology Apprentice 

Value Story
As a retailer, I would like a discount service that is able to provide users the best price that they're eligible for, based on the items they are purchasing, so that cashiers do not need to manually determine the correct combinations of discounts to use and not use.

Additional Context
Cashiers often choose a non-optimal combination of discounts to use, which results in customers paying more for their purchases, and (more importantly) this ends up being inconsistent across multiple customers, resulting in some customers being upset because they paid more for the same items on the same day as someone else.

This drop in customer satisfaction is estimated to cause the company a loss of 3% of total annual sales, due to increased return rates, reduced number of purchases, reduced total purchase amounts, and lower customer retention rates.
Features
All-Cart Discounts
Only one all-cart discount can be applied at any given time
Possible Conditions:
Optional: At least a quantity n of a specific item
Optional: At least a quantity n of an item with a set of specific properties is in the cart
Optional: At least n items are in the cart
Required: Specific eligible days
Discount Types:
Percentage discount
Dollar amount discount
Example all-cart discounts:
20% off entire cart St. Patrick's Day only
15% off entire cart when you buy any 3 sweaters
$5 off entire cart when you buy 2 copies of "Clean Code"
Multiple-Quantity Discounts
Only one multiple-quantity discount can be applied to a given item at any given time
Possible Conditions:
Required: At least a quantity n of:
A specific item; or
A set of items that have a set of specific properties
Required: Specific eligible days
Discount Types:
Percentage discount
Dollar amount discount
Buy n get one free
Example multiple-quantity discounts:
All pants are 50% off when you buy 2 or more pairs of pants
Buy 10 2-Liter bottles of Coca-Cola products, get the 11th free
Single Item Discounts
Only one single-item discount can be applied to a given item at any given time
Possible Conditions:
Required:
Specific item only
Item has a set of specific properties
Required: Specific eligible days
Discount Types:
Percentage discount
Dollar amount discount
Example single item discounts:
Save 10% on all books
25% off Keebler Grasshoppers
Other Constraints:
The discounted total must not be less than 40% of the original (pre-discount) total
This was the old "check" that is currently being used by cashiers (it's an "easy" check for them).  Will be phased out and replaced with the margin check.
The margin on the discounted total for the cart must not be less than 2%

