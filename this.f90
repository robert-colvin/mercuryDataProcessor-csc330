        program this

        DOUBLE PRECISION, DIMENSION (3260) ::  lat
        DOUBLE PRECISION, DIMENSION (3260) :: long
        DOUBLE PRECISION, DIMENSION (3260) :: merc
        DOUBLE PRECISION, DIMENSION (3260) ::  mom
        DOUBLE PRECISION, DIMENSION (3260) ::  dad

        INTEGER cnt

        open ( unit=8, file='./mercurydata.csv', status='old')


10      read (8,*,END=20) lat(cnt), long(cnt), merc (cnt)
        
 !       print *, 'latitude: ', lat (cnt) 
  !      print *, 'longitude: ', long (cnt) 
   !     print *, 'mercury: ', merc (cnt)
    !    print *
       
        cnt = cnt + 1

        goto 10 	
20      close(8)

        cnt = 0	
        

        
        do i=0,3260,1
                do j=i+1,3260,1
                        mom(j) = (SIN((lat(i)-lat(j))/2.0))**2+COS(lat(i)) * COS(lat(j))*(SIN((long(i)-long(j))/2.0))**2 
                        dad(j) = ATAN2(sqrt(mom(j)), sqrt(1.0-mom(j)))
                        mom(j) = 6373000.0 * (2.0 * dad(j))
                        
                        print *, mom(j)
                enddo
        enddo
        end

