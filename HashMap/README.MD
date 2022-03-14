# HashMap
`General purpose of HashMap as a Data Structure is to store values pointed by different unique keys. Here the term key speaks for itself and we can guess by the name that a key is something we can use to search for the desired value, where both key and value can be of different data-types like String,Integer,Character and much more.`

## Internal working of a HashMap
HashMaps on the inside are nothing but a normal array that can be used to store different values at the specified indices. But the mismatch here is that for an array of size n, values can only be accesed using integers ranging from 'zero' to 'n-1', however the key can vary from 'zero' to upper limit of a 32-bit integer.

To fill this gap we have to utilise some **hash** function which will convert the key to some valid integer (hash value) lying in the range of array's size.

**Collision:** Another major concern regarding this approach is to produce a unique hash value for every different key. It is quite impossible to come up with any algorithm or method to avoid the situation of collision, which is when hash function produces same hash value for different keys. This will obviously create problems when we try to insert values or search for values in the array.

There are different techniques to deal with this problem, most popular and simple techniques are:
* [Linear Probing](https://en.wikipedia.org/wiki/Linear_probing#:~:text=Linear%20probing%20is%20a%20scheme,by%20Gene%20Amdahl%2C%20Elaine%20M.)
* [Quadratic Probing](https://en.wikipedia.org/wiki/Quadratic_probing)
* [Double hashing](https://en.wikipedia.org/wiki/Double_hashing#:~:text=Double%20hashing%20is%20a%20computer,data%20structure%20on%20a%20table%20.)

 
**Double hashing:** We are already using a *primary hash* function that converts the key to its integer hash value which will be used as an index location to store the value in array. Now, we have to use another *secondary hash* function to deal with collision of different keys.

Suppose a situation where we are trying to insert a value at some index in array but there is already some value stored because some previous key's hash value collides with the hash value of current key, we will have to look for some another empty location to store the current value. We will use the expression below to look for next empty location.

```
index  = primary_hash(key) + i * secondary_hash(key)
```
Here, **index** is the next empty location we will look for,  **i** is iterating variable which starts from 'zero' and goes on until we find an empty location in the array.