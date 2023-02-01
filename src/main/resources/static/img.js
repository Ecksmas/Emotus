const img = [
{file: "/flowers.jpg", description:"positive"},
{file: "/veryhappy.jpg", description:"very positive"},
{file: "/neutral.jpg", description:"Neutral"},
{file: "/sad.jpg", description:"Negative"},
{file: "/verysad.jpg", description:"Very negative"},
]

const sentiment = document.querySelector('.sentiment span');
const sentimentText = sentiment.textContent;
console.log(sentimentText);

const imageContainer = document.querySelector('#imageContainer');
let imageSrc;



 if (sentimentText === "very positive") {
     imageSrc='/veryhappy.jpg';
           } else if (sentimentText === "positive") {
        imageSrc='/flowers.jpg';
           } else if (sentimentText === "negative") {
     imageSrc='/sad.jpg';
           } else if (sentimentText === "very negative") {
     imageSrc='/verysad.jpg';
           } else {
     imageSrc='/neutral.jpg';
           }
const image = document.querySelector('.picture');
image.src = imageSrc;
imageContainer.appendChild(image);

















