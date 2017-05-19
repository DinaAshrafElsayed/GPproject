Project ShareIt 
==

Description:


### Our Persona
    - Hamada (22-50)
    - Mervat (22-50)
    - System
    - Admin


## Milestone (Share_Item)

##### 0.1- Hamada: can register as new user by:
- name
- email
- password
- pic
- address
- gender

##### 0.2 System: after valid registration 
- System: run HD5 hashing on Hamada password before saving it to db, and password should not be returned to client as plaintext
- System: initiate Hamada balance by 100 point

##### 0.3 Hamada: can login by his email and password  
- System: validate Hamada email and password
- System: do login and redirect Hamada to his page  
---
##### 1.1- Mervat: can create item for sharing:  
- Mervat should provide :
  - item name
  - category
  - sharing description
  - how many points needed for sharing.

##### 1.2- System: checks for the max points allowed for this item by item category item.points <= category.points
---
##### 2.1- Hamada: can view all items 
##### 2.2- Hamada: can search  by :
- Location | category | name
##### 2.3- Hamada: can request item to borrow: 
- Hamada should provide: 
    - item from & time to.
    - meeting point .

##### 2.4- System can check the item available or not and validate time information.
---

##### 3.1- Hamada: can cancel request before Mervate accept the request. if fared accepted the request Hamada will not be able to cancel the request.
---

##### 4.1- Mervat: can view all here request
##### 4.2- Mervat: can select Hamada’s request to accept.
##### 4.3- Mervat: can delete Hamada’s request.
##### 4.4- System: if Mervat accept Hamada’s request
- System: in one single transaction 
- System: should Lock the item for Hamada
- System: Transfer the points from Hamada to Mervat's balance.
- System: Send notification to Hamada with
  - points deducted
  - interval time Hamada will borrow the item
  - meeting point with Mervate


-------------------------------------------------------------------------------
# Our DB Tables:
##### 1. T_User 
- id (pk, integer)
- username(nn,varchar(20) 
- email(unq, varchar(100)
- Address  (another table)
- password(byte)
  - gender (in other table or an enum in user class can mapped values from 0/1 to “male/female”)
  - image-url(varchar(200)
  - points(integer).


##### 5.T_Address
- id (integer)
- user (user)
- country (other lookup table)
- city (other lookup table)
- State (other lookup table)


##### 2.T_Item
- id
 - name (vch(20))
 - description (vch(200))
 - category (another table)
 - is_available (bool)
 - publish_date(sysdate).

#####  3.T_Category
  - id (integer)
  - name (vch(200))
  - max_point (integer)

##### 4.T_Activity
  - id (integer)
  - item (item)
  - from_user (user)
  - to_user (user)
  - meeting_point (vch(200))
  - status (can be another table or enum)
  - time_from (date)
  - time_to (date)
  - activity_deleted(bool)
