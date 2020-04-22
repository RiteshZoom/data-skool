//how to run : spark-submit --class MainApp  --master 'local[4]' encoder_2.11-1.0.jar encoder thrift://localhost:9083 /user/hive/warehouse encoder
//how to run : spark-submit --class MainApp  --master 'local[4]' encoder_2.11-1.0.jar <app_name> <thrift_uri> <ware_house> <input_db_name>

import org.apache.spark.sql.SparkSession
import scala.io.Source
import scala.util.control.Breaks._
import java.io._

object MainApp {

  def main(args: Array[String]) {

    val appName = args(0)
    val inputFile = args(1)
    val outputFolder = args(2)
    val spark = SparkSession.builder().master("local[2]").appName(appName).getOrCreate();
    import spark.implicits._

    println()
    print(appName)
    println()
    print(inputFile)
    println()
    print(outputFolder)
    println()

    val rle = new RunLengthEncoderImp();
    var validity:Int=0

    // Read the text file 
    val valid="""([a-zA-Z1-9!@#$%^&*~:"',.<>?/|{}+-_=`])+""".r
    val all = Source.fromFile(inputFile).getLines.flatMap(valid.findAllIn).toList
    
    breakable {
        for (str <- all) {
            if(rle.isAllDigits(str)){
                // println(str)
                validity=1        
            }
            else{
                println("\n"+"INVALID FILE: Rejected"+"\n")
                break
            }
        }
    }
    // Iterate through the each of the line and encode the line. Put this encoding logic into EncoderAlgoImplementation file
    
    
    
    val fSource = Source.fromFile(inputFile)   
    
    var outputString=""
    
    if(validity==1){
        for(line<-fSource.getLines) 
            {  
                val wordsLst = line.split(" ").toList
                for(word <- wordsLst){
                    val encoded_word=rle.encodeString(word).get
                    outputString= outputString + encoded_word + " "   
                }
                outputString= outputString + "\n"
            } 
    }



    // check for the diff in sizes of original data and latest encoded data
    // If both are same discard it, else write the encoded data to to output folder

    val inFile=new File(inputFile)
    
    if(inFile.length>outputString.length){
        rle.writeFile(outputFolder,outputString)
        fSource.close()
    }
    else{
        fSource.close()
        outputString=""    
    }
    spark.stop()
    
    }
}
