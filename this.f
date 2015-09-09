        program this

        DOUBLE PRECISION, DIMENSION (3260) ::  lat
        DOUBLE PRECISION, DIMENSION (3260) :: long
        DOUBLE PRECISION, DIMENSION (3260) :: merc

        open ( unit=8, file='./mercurydata.csv',
     C      status='old')

        real cnt=0
10      read (8,*,END=20) lat(cnt),long(cnt),merc (cnt)
        
        print *, 'latitude: ', lat (cnt) 
        print *, 'longitude: ', long (cnt) 
        print *, 'mercury: ', merc (cnt)
        print *
       
        cnt = cnt + 1

        goto 10 
20      close(8)


        
        end

