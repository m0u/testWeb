
<html>
<head>
<title></title>
<!-- Apply styles to html elements -->
<style>
*{
margin:0; padding:0; boxsizing:border-box;
}
header{
width: 100%; height: 100vh;
background-color: White;
background-repeat: no-repeat;
background-size: cover;
}
nav{
width: 100%; height: 10vh;
background: GreenYellow;
display: flex; justify-content: space-between;
align-items: center;
}
nav .mainmenu{
width: 40%;
display: flex; justify-content: space-around;
}
main{
width: 100%; height: 85vh;
display: flex; justify-content: center;
align-items: center;
text-align: center;
}
section h3{
letter-spacing: 3px; font-weight: 200;
}
section h1{
text-transform: uppercase;
}
section div{
animation:changeborder 10s infinite linear;
border: 5 px Solid Yellow;
font:40 px;
fontcolor: Gold;
}
@keyframes changeborder{
0%
20%
40%
}
</style>
</head>
<body>
<!--Let us create a simple menu using the navigation tags-->
<!--Use header to indicate that menu will be a part of header -->
<header>
<nav>
<!--<div class = "logo" <h3 style="color:white;">MYLOGO</h3></div>-->
<!--Lets define the menu items now-->
<div class = "mainmenu">

<a href="Technical documentation.jsp">Technical documentation</a>
<a href="fileupload.jsp">Database</a>
<a href="biclustering.jsp">Data mining</a>
<a href="datavisualization.jsp">Data visualization</a>
<a href="aboutus.jsp">About us</a>
</div>
</nav>



<!--This example uses the default bootstrap stylesheet-->
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!--provides a full-width container that can expand or collapse based on the size of viewport-->
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 col-sm-12 col-xs-12">
            <nav id="navbar">
                <h3>Technical Documentation</h3>
                <!--content stacking for smaller screens-->
                <ul class="nav nav-pills nav-stacked">
                 <!--internal linking to the respective sections-->
                    <a class="nav-link" href="#Introduction" rel="internal">
                        <li>Knowledge discovery in Biodiversity Data: Introduction</li>
                    </a>
                    <a class="nav-link" href="#Aim_motivation" rel="internal">
                        <li>Motivation and Contribution</li>
                    </a>
                    <a class="nav-link" href="#Data_mining_approach" rel="internal">
                        <li>Data mining approach</li>
                    </a>
                    <a class="nav-link" href="#Addressed_area1" rel="internal">
                        <li>Indian Mangrove</li>
                    </a>
                    <a class="nav-link" href="#Addressed_area2" rel="internal">
                        <li>Sundarban Mangrove</li>
                    </a>
                 </ul>
            </nav>
        </div>
        <div class="col-md-10 col-sm-12 col-xs-12">
            <main id="main-doc">
                <section class="main-section" id="Introduction" >
                 <!--basic styling for the headings, better practice to do the same in a css file as the styling is same for all headers-->
                    <h3 style = "background: black; color: white">Knowledge discovery in Biodiversity Data: Introduction</h3>
                    <article>
                    <p>Biodiversity is essential for the economic and ecological security of human beings. 
                    Since a few decades, biodiversity is being eroded in an alarming rate due to rapid urbanization and alteration of habitats as well. 
                    Thus conservation of ecosystem is in utmost need.</p>
                    <p> On the other hand, knowledge discovery in database (KDD) is the process of determining logical, unique, potentially useful, and obvious underlying patterns in data. 
                    KDD, in the domain of biodiversity can be termed as computational biodiversity that is the application of various statistical and algorithmic approaches on the biodiversity data. 
                    Since the last few decades, the declining rate of biodiversity globally poses risk to many others. 
                    Therefore, there is an emerging need to recognize and assess complex ecological questions. 
                    Along with the statistical measures employed by the ecologists, computer-science researchers comprehends the prospect of analytical study for achieving the solution for the adverse environmental issues.  
                    </p>
                     </section>
                <section class="main-section" id="Aim_motivation">
                    <h3 style = "background: black; color: white">Motivation and Contribution</h3>
                    <article>
                        <p> Forests are the natural security forces that have immense ecological service in controlling a number of climate catestrophy, preventing soil erosion, inhibiting inward ingression of sea in mangrove area and providing ecological niche for animals and livelihood for humans. 
                        Inspite of the great importance, loss of forest is prominent in India.
                        For example, a case study reveals that in between 1986 to 2012, 124.418 sq. km. mangrove forest cover has been lost.
                        Different causes like over exploitation and illegal forest cutting, pollution, climate change etc. are identified as the most dominant factors for degradation of forest ecosystem. 
                        Thus there is an urgent need of cause-effect analysis that would be helpful in safeguarding this precious ecosystem through proper management. 
                        Mainly statistical analysis techniques are used in a few research articles but those are only confirmatory analysis techniques with respect to researchers’ understanding.
                        But data mining tools perform exploratory analysis where it is concerning with detecting and describing pattern within data, identifying predictor variables and discovering the forms of relationships between predictors and response. 
                        The beneficial use of data mining is already proven where statistical analysis is unable to find out whether there is any relationship between abiotic and biotic factors on ichthyoplankton samples collected from a freshwater reservoir of Legal Amazon. Here, the use of data mining technique, the Apriori algorithm, helps in generating association rule regarding the understanding of the process of fish spawning in Tocantins River. 
                        Thus knowledge discovery in data mining process is capable of identifying valid, potentially useful and understandable pattern  which is not a new application in biodiversity domain.
                        But very few research contribution can be found particularly in this domain.
                        
                        </p>
                        <p>
                        Our main contribution can be summarized in two broad categories:
                        <li> Digitized downloadable datasets formations: Datasets on mangroves in Sundarban, as well as India, based on the gathered data from multiple online resources are formed. 
                        Multiple number of mangroves along with their associates are identified from different observational studies taken from both the published and unpublished literature.  </li>
                        <li> Application of domain specific algorithms: Novel algorithms along with information retireval strategy is proposed. </li>
                        
                        </p>
                     
                        </artice>
                </section>
                <section class="main-section" id="Data_mining_approach">
                    <h3 style = "background: black; color: white">Data mining approach</h3>
                    <article>
                        <p>Data mining tools and packages in computer science yields interesting analytical results that could be applied on biodiversity data. 
                        Here our intension is to design a novel and efficient, domain specific data mining algorithm.
                        The proposed algorithm will become useful for the researchers and for the users working on the primary biodiversity data for the conservation and management of the fragile ecosystem. </p>
                        <p>
                      
                        <!--creating list using html-->
                        <li>Frequent itemsets</li>
                        <li>Frequent closed itemsets</li>
                        <li>Biclustering</li>
                        <li>Association rule mining</li>
                        </p>
                        
                        
                        </article>
                </section>
                <section class="main-section" id="Addressed_area1">
                    <h3 style = "background: black; color: white">Indian Mangrove</h3>
                    <article>
                        Mangroves represent the littoral forest ecosystem. They are also termed as halophytes as they have the salt-tolerant capability.
                        According to the state forest report(2019) published by Forest Survey of India, mangrove cover in India is 4975 sq km which is 0.15 percent of the total geographical area.
                        The report states the mangrove occurrence in total 12 states and union territories. 
                        Total 40% of the mangrove cover is the open mangroves.
                        Whereas, very dense mangroves, and moderately dense mangroves cover 30% of the mangrove cover.
                        Noticeably, West Bengal covers 42.45% of the India's mangorve cover.
                        <code> Indian Sundarban is forming the mangrove cover of West Bengal and it is the largest mangrove in the World.
                       Pichavaram Mangrove Forests, in Tamil Nadu, is the second largest in the world. 
                       
                        </code>
                        Other major states having mangrove covers are Gujrat, Andhra Pradesh, Odisha, and Andaman.
                        All other mangroves are situated along the east and west coast of India. 
                    </article>
                </section>
                <section class="main-section" id="Addressed_area2">
                    <h3 style = "background: black; color: white">Sundarban Mangrove</h3>
                    <p>
The Sundarbans mangrove forest, one of the largest mangroves in the World, prevails on the delta of the Ganges, Brahmaputra and Meghna rivers on the Bay of Bengal.
Both the saline tidal water of Bay of Bengal and the fresh water flow of Ganga-Brahmaputra, are the underlying reasons for this unique habitats in this ecologically stressed environment.
Specially adopted floral and faunal biodiversity are found in Sundarban along the different estuarine regions.
Largest mangrove forest in the World along with large number of Royal Bnegal Tigers promote Sundarban as the World Heritage Site as identified by UNESCO.																														
                                                                                                                                              
                    </p>
                </section>
            </main>
        </div>
    </div>
</div>
</html>

 


