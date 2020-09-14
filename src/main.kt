import java.util.Scanner
import java.util.ArrayList
val read = Scanner(System.`in`)


data class partner(var partid: Int, var partname: String, var partamount: Int)
data class gpurchases(var purid: Int, var purname: String, var purgold: Float, var puramount : Float)
data class smith(var smid: Int, var smname: String, var givngold: Float, var touch: Double)
data class readyGold(var rid: Int, var rname: String, var rgold: Float, var ramount : Float, var rtouch : Float)
data class stock(var sid: Int, var sname: String, var gweight: Float, var stouch: Float)
data class returnedItems(var reid: Int, var rename:String,var regoldweight: Float, var retouch : Float)
data class customer(var cid:Int, var cname:String,var itemname : String, var cprice:Float)



var partnerslist = arrayListOf<partner>()
var purchasedgoldlist = arrayListOf<gpurchases>()
var smithlist = arrayListOf<smith>()
var rgoldlist = arrayListOf<readyGold>()
var stocklist = arrayListOf<stock>()
var returnedlist = arrayListOf<returnedItems>()
var customerlist = arrayListOf<customer>()


var cash : Float = 0F
var total_purgold : Float = 0F
var total_smgold : Float = 0F
var total_rgold : Float = 0F


fun main() {

    println("welcome to jwellery management system")
    home()
}



    fun home() {

        println("1 -> PARTNER DETAILS")
        println("2 -> PURCHASE DETAILS")
        println("3 -> DETILAS OF GOLD GIVEN/RETURN TO/FROM SMITH")
        println("4 -> SELL GOLD TO CUSTOMER")
        println("5 -> DETIALS OF OLD GOLD GIVEN FOR MELTING")
        println("6 -> REPORTS")
        println("choose your option")
        val read = Scanner(System.`in`)
        var n = read.nextInt()
        when (n) {
            1 -> partnerDetails()
            2 -> purchaseDetails()
            3 -> smithDetails()
            4 -> sellToCustomer()
            6 -> reports()

            else -> { // Note the block
                print("coming soon!!")
            }
        }

    }

        fun partnerDetails() {

            println("partner's details")
            println(partnerslist)

            println("choose an option:-")
            println("1-> add partner")
            println("2-> edit partner")
            println("3-> go back")
            val read = Scanner(System.`in`)
            var n = read.nextInt()
            when (n) {
                1 -> addPartner()

                3 -> home()
                else -> {
                    print("number is neither 1 nor 3")

                }
            }
        }

        fun addPartner() {
            println("enter id,name,amount of partner")
            var id = read.nextInt()
            var name = readLine()
            var amount = read.nextInt()
            var p2 = partner(id, "$name", amount)
            partnerslist.add(p2)
            cash = cash + amount
            println(p2.toString());
            println("partner added successfully")
            partnerDetails()

        }
fun purchaseDetails() {
    println("purchase details")
    println(purchasedgoldlist)
    println("choose an option:-")
    println("1-> add gold purchases")
    println("2-> add ready made gold purchases")
    println("3-> go back")
    val read = Scanner(System.`in`)
    var n = read.nextInt()
    when (n) {
        1 -> addGoldPurchases()
        2 -> addReadyGoldPurchases()

        3 -> home()
        else -> {
            print("number is neither 1 nor 3")
        }
    }
}
    fun addGoldPurchases(){
             println("enter the purchased gold detials , id ,purchased details,purchased gold in kg")
             var id = read.nextInt()
             var name = readLine()
             var gold = read.nextFloat()
             println("enter the gold rate of 1 kg as of today")
             var kggold = read.nextFloat()
             var amount = gold * kggold
             var pur2 = gpurchases(id, "$name", gold, amount)
        if (cash < amount){
            print("no enough money")
            purchaseDetails()
        }
        else {
                    cash = cash - amount

                purchasedgoldlist.add(pur2)
                total_purgold = total_purgold + pur2.purgold
                purchaseDetails()
         }}
fun addReadyGoldPurchases(){
    println("enter the  detials , id ,purchased details,purchased ready made gold in g,touch")
    var id = read.nextInt()
    var name = readLine()
    var gold = read.nextFloat()
    var touch = read.nextFloat()
    var quantity = read.nextInt()
    println("enter the gold rate of 1 g as of now")
    var gramgold = read.nextFloat()

    var amount = gold * gramgold + touch
    if (cash < amount){
        println("no enough money")
        purchaseDetails()
    }
    else{
    cash = cash - amount
    var rg2 = readyGold(id, "$name", gold, amount,touch)
    rgoldlist.add(rg2)
    total_rgold = total_rgold + rg2.rgold
    println("successfully added ready made gold purchase")
    var price = amount + touch
    var s2 = stock(id,"$name",gold,touch)
    stocklist.add(s2)
    println("successfully added to stock")
    purchaseDetails()
}}
fun smithDetails(){
    println("DETAILS OF GOLD GIVEN TO SMITH")
    println(smithlist)
    println("choose an option:-")
    println("1-> add smith")
    println("2-> edit smith")
    println("3 -> add to stock(returned items by smith)")
    println("4-> go back")

    var n = read.nextInt()
    when (n) {
        1 -> addSmith()
        3 -> addStock()
        4 -> home()
        else -> {
            print("number is neither 1 nor 3")
            smithDetails()
        }
    }

}
fun addSmith(){
    println("enter id ,smith name ,gold given to smith")
    var id = read.nextInt()
    var name = readLine()
    var smgold = read.nextFloat()
    var tch = smgold * 0.005
    var sm2 = smith(id, "$name", smgold, tch)


    if (total_purgold < smgold){
        println("purchased gold not enogh  ")
        smithDetails()
    }
    else {
        total_purgold = total_purgold - smgold
        smithlist.add(sm2)
        println("smith added successfully")
        total_smgold = total_smgold + sm2.givngold
    }
    smithDetails()
}
fun addStock(){

    println("enter id ,smith name ,gold retuned by smith and touch,")
    var id = read.nextInt()
    var name = readLine()
    var smgold = read.nextFloat()
    var touch = read.nextFloat()
    var re2 = returnedItems(id,"$name",smgold,touch)
    returnedlist.add(re2)
    println("successfully added to returned items list")
    var s2 = stock(id,"$name",smgold,touch)
    stocklist.add(s2)
    println("successfully added to stock")
    smithDetails()


}
fun sellToCustomer(){
    do {
        println("select the item to sell")

        println(stocklist)
        println("enter the item id ,gold weight and touch,quantity")
        var id = read.nextInt()
        var name = readLine()
        var gweight = read.nextFloat()
        var touch = read.nextFloat()
        var quantity = read.nextInt()
        println("enter the gold today's 1 g gold rate")
        var gramgold = read.nextFloat()

        var price = gramgold * gweight + touch
        cash = cash + price
        println("enter customer id and name")
        var cid = read.nextInt()
        var cname = readLine()
        var s2 = customer(cid, "$cname", "$name",price)
        stocklist.removeAt(stocklist.binarySearch(stock(id, "$name", gweight, price), compareBy<stock> { it.sid }.thenBy { it.sname }))
        println("item sold")
        println("sell more items enter 1 for yes")
        var n = read.nextInt()
    }while(n==1)
    home()


}
fun reports(){
    println("cash in hand = $cash")
    println("remaing purchased gold $total_purgold")

}