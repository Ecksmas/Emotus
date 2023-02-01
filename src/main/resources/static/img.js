const img = [
{file: "/flowers.jpg", description:"positive"},
{file: "/veryhappy.jpg", description:"very positive"},
{file: "/neutral.jpg", description:"Neutral"},
{file: "/sad.jpg", description:"Negative"},
{file: "/verysad.jpg", description:"Very negative"},
]

/*document.querySelector("sentiment")*/
const sentiment = document.querySelector('.sentiment span');
const sentimentText = sentiment.textContent;
console.log(sentimentText);

/*const imageContainer = document.querySelector('.picture');
let imageSrc;*/

function showImage(sentimentText) {
document.getElementByClassName("sentiment").innerHTML = sentimentText;
 if (sentimentText === "very positive") {
     img.src='/veryhappy.jpg';

           }

/*const image = document.createElement('img');
image.src= imageSrc;*/
/*imageContainer.appendChild(Image);*/

/*if (sentiment === "very positive"){

 return img.src="/veryhappy.jpg";

    }

 if (sentimentText === "neutral") {
      return img.src="/neutral.jpg"
    }

 if (sentimentText === "negative")
    return img.src="/sad.jpg";

    }

 if (sentimentText === "very negative") {
        return img.src="/verysad.jpg";


    }*/
}
