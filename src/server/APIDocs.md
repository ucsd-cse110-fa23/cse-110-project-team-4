## For all JSON Bodies, simply create the JSON, and write JSON.toString() to the output Stream

## Get recipe list for user:  
GET: localhost:8100/recipeList?=\<user id\> \
Returns: JSONArray of the recipe list as string 

## Get recipe: 
POST: localhost:8100/recipe?=\<recipe id\> \
Returns: JSON of recipe as string

## Create recipe: 
POST: localhost:8100/recipe \
Body: \
{ \
    "name": "Makai plate", \
    "mealType": "dinner", \
    "details": "Poke bowl with salmon and ahi tuna", \
    "userId": "65614b0c44879f466638921b" \
} \
Returns: JSON of recipe as string 

## Edit recipe: 
PUT: localhost:8100/recipe \
Body: \
{ \
    "name": "Huli Huli Rib bowl", \
    "details": "Yummy rip plate with huli huli sauces", \
    "id": "655db7d359128f35492078c1" \
} \
Returns: JSON of recipe as string 

## Delete recipe:
DELETE: localhost:8100/recipe?=\<recipe id\> \
Returns: JSON of recipe as string 

## Create user 
POST: localhost:8100/user \
Body: \
{ \
    "username": "iAmWeird",  \
    "password": "p455w0rd!" \
} \
Returns: Successfully created user <username> 

## Create user 
POST: localhost:8100/user \
Body: \
{ \
    "username": "iAmWeird",  \
    "password": "p455w0rd!" \
} \
Returns: if correct, userId, else "Invalid Login Credentials"

