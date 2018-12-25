/*
 * @file    rsa.c
 * @author  �ۼ��� ������ / 2014038304
 * @date    2018_11_21
 * @brief   mini RSA implementation code
 * @details ���� ����
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "miniRSA.h"
#include <math.h>

uint p, q, e, d, n;

/*
 * @brief     ��ⷯ ���� ������ �ϴ� �Լ�.
 * @param     uint a     : �ǿ�����1.
 * @param     uint b     : �ǿ�����2.
 * @param     byte op    : +, - ������.
 * @param     uint n      : ��ⷯ ��.
 * @return    uint result : �ǿ������� ������ ���� ��ⷯ ���� ��. (a op b) mod n
 * @todo      ��ⷯ ���� �����÷ο� ��Ȳ�� ����Ͽ� �ۼ��Ѵ�.
 */
uint Mod(uint n, uint m){ // ������(%) ����
	uint result = n;
	while(result >= m){
		result = result - m;
	}
	return result;
}
uint Div(uint n, uint d){ // ������(/) ����
	uint count=0;
	while(n>=d){
		n = n-d;
		count++;
	}
	return count;
}

uint ModAdd(uint a, uint b, byte op, uint n) {
	uint result;
	a = Mod(a,n);
	b = Mod(b,n);
	if(op=='+'){
		if (a + b <= a) { //�����÷ο찡 �߻��ϴ� ���
			result = Mod((a - n + b),n);
		}
		else result = Mod(a+b,n);
	}
	else{
		if(a<b){ //������ ���� �ʰ� ����
			result = Mod((n+a-b),n);
		}
		else result = Mod(a-b,n);
	}
    return result;
}

/*
 * @brief      ��ⷯ ���� ������ �ϴ� �Լ�.
 * @param      uint x       : �ǿ�����1.
 * @param      uint y       : �ǿ�����2.
 * @param      uint n       : ��ⷯ ��.
 * @return     uint result  : �ǿ������� ������ ���� ��ⷯ ���� ��. (a x b) mod n
 * @todo       ��ⷯ ���� �����÷ο� ��Ȳ�� ����Ͽ� �ۼ��Ѵ�.
 */

uint Modmul(uint a,uint b,uint n){
	uint asq;
	a = Mod(a,n);
	b = Mod(b,n);
	if(a == 0 || b == 0) return 0; //a,b �Ѵ� 0�� ���
    if(a == 1) return b; //a�� 1�� ���
    if(b == 1) return a; //b�� 1�� ���
	asq = Modmul(a, Div(b,2), n); //b�� ������ ���� ����Լ��� ����
	if((b & 1) == 0) return ModAdd(asq,asq,'+',n); //b�� ¦���� ���
	return ModAdd(a,ModAdd(asq, asq,'+',n),'+',n); //b�� Ȧ���� ���
}

/*
 * @brief      ��ⷯ �ŵ����� ������ �ϴ� �Լ�.
 * @param      uint base   : �ǿ�����1.
 * @param      uint exp    : �ǿ�����2.
 * @param      uint n      : ��ⷯ ��.
 * @return     uint result : �ǿ������� ���꿡 ���� ��ⷯ ���� ��. (base ^ exp) mod n
 * @todo       ��ⷯ ���� �����÷ο� ��Ȳ�� ����Ͽ� �ۼ��Ѵ�.
               'square and multiply' �˰����� ����Ͽ� �ۼ��Ѵ�.
 */

uint ModPow(uint base, uint exp, uint n) {
	uint result=1;
	uint index=0; //base�� ����
	uint pows = 1;
	uint * powers = (uint*)malloc(sizeof(uint)*(pows+1)); //base�� 2^i��(i=1,2,3...31)...�� �����ϱ� ���� �迭
	base = Mod(base,n);
	powers[pows] = Modmul(base,base,n);
	while(1){ //base�� 2^i���� ����� �迭�� ������
		pows++;
		powers = (uint*)realloc(powers,sizeof(uint)*(pows+1)); //�迭�� �����Ҵ�
		powers[pows] = Modmul(powers[pows-1],powers[pows-1],n); //�迭�� �� �� 
		if(pows==31) break; //base�� 2^31�� ���� ����(32bit�̱� ����)
		if((1<<(pows+1)) > exp) break; //base�� ���� 2�� �������� ���ϸ� exp���� Ŀ���� ��� ����
	}
	while(1){ //2^i�µ��� ������ exp�� ���� ����� result�� ������
		if(index+(uint)(1<<pows) <= exp){ //base�� ������ index�� 2^i�µ��� ���ذ��� exp�� ���� ����
			index = index + (uint)(1<<pows);
			if(pows==0){
				result = Modmul(result,base,n); //2^0���� ��� base�� ����
				break;
			}
			else result = Modmul(result,powers[pows],n); //base�� 2^i���� ����
		}
		pows--;
		if(index==exp) break;
	}
	free(powers);
    return result;
}

/*
 * @brief      �Էµ� ���� �Ҽ����� �Էµ� Ƚ����ŭ �ݺ��Ͽ� �����ϴ� �Լ�.
 * @param      uint testNum   : ���� ������ Ȧ��.
 * @param      uint repeat    : �Ǵ��Լ��� �ݺ�Ƚ��.
 * @return     uint result    : �Ǵ� ����� ���� TRUE, FALSE ��.
 * @todo       Miller-Rabin �Ҽ� �Ǻ����� ���� Ȯ������ ����� ����Ͽ�,
               �̷������� 4N(99.99%) �̻� �Ǵ� ���� �����ϵ��� �Ѵ�. 
 */
uint randomRange(uint n1, uint n2){ //n1~n2�߿��� ������ ���� �̴� �Լ� 
	return Mod(rand(),(n2-n1+1)) + n1;
}

bool IsPrime(uint testNum, uint repeat) { //�з�-��� ������� �Ҽ��� �Ǻ�
    bool result = 0;
	uint n = testNum-1;
	uint d=n;
	uint s=0;
	uint i,r;
	uint a;
	while(Mod(d,2)==0){ //n-1�� d * 2^s ���·� ����
		s++;
		d = d>>1;
	}
	for(i=0; i<repeat; i++){ 
		a = randomRange(1,testNum-1); //testNum���� �۰� 1���� ũ�ų� ���� a�� �������� ����
		if(ModPow(a,d,testNum)==1) result = 1;
		else{
			for(r=0; r<s; r++){
				if(ModPow(a,d*(uint)pow(2.0,(int)r),testNum)==testNum-1) result = 1;
			}
		}
		if(result==0){ //�ѹ��� ���� �� ������ �������� ���ϸ� 0�� return(�Ҽ��� �ƴ�)
			return result;
		}
		//printf("repeat : %d  a: %d\n",i+1,a);
	}
	return result; //��� ��쿡 ������ �����ϸ� 1�� return(�Ҽ���)
}

/*
 * @brief       ��ⷯ �� ���� ����ϴ� �Լ�.
 * @param       uint a      : �ǿ�����1.
 * @param       uint m      : ��ⷯ ��.
 * @return      uint result : �ǿ������� ��ⷯ ���� ��.
 * @todo        Ȯ�� ��Ŭ���� �˰����� ����Ͽ� �ۼ��ϵ��� �Ѵ�.
 */
uint ModInv(uint a, uint m) {
    uint result;
	uint x[10000],y[10000];
	uint r[10000],q[10000];
	int i=2;
	x[0] = 1; x[1] = 0; y[0] = 0; y[1] = 1; r[0] = a; r[1] = m;
	while(1){
		q[i] = Div(r[i-2],r[i-1]);
		r[i] = Mod(r[i-2],r[i-1]);
		x[i] = ModAdd(x[i-2],Modmul(q[i],x[i-1],m),'-',m);
		y[i] = ModAdd(y[i-2],Modmul(q[i],y[i-1],m),'-',m);
		if(r[i]==0){ //�������� 0�� �� ��� result�� �Ҵ��ϰ� ����
			result = x[i-1];
			break;
		}
		i++;
	}
	result = Mod(result,m);
	return result; //������ return
}

uint GCD(uint a, uint b) {
    uint prev_a;
    while(b != 0) {
        //printf("GCD(%u, %u)\n", a, b);
        prev_a = a;
        a = b;
        while(prev_a >= b) prev_a -= b;
        b = prev_a;
    }
    //printf("GCD(%u, %u)\n\n", a, b);
    return a;
}

/*
 * @brief     RSA Ű�� �����ϴ� �Լ�.
 * @param     uint *p   : �Ҽ� p.
 * @param     uint *q   : �Ҽ� q.
 * @param     uint *e   : ����Ű ��.
 * @param     uint *d   : ����Ű ��.
 * @param     uint *n   : ��ⷯ n ��.
 * @return    void
 * @todo      ���� �ȳ� ������ ���ѻ����� �����Ͽ� �ۼ��Ѵ�.
 */

void showbits(uint num){ //uint�� 2������ ������ִ� �Լ�
	uint i = 1<<31;
	int count;
	for(count=0; count<32; count++){
		if(num & i) printf("1");
		else printf("0");
		if((count+1)%8==0) printf(" ");
		i = i>>1;
	}
	printf("\n");
}

void miniRSAKeygen(uint *p, uint *q, uint *e, uint *d, uint *n) {
	//p,q,n ����
	uint gcd_n; //����(n)�� ������ ����
	uint temp; //32bit�� e�� ����� ���� �ӽ÷� ����� ����
	while(1){
		while(1){
			*p = rand();
			*p = *p|(1<<15); //rand�� 16��° bit�� �������� ���ϹǷ� p�� 16��° bit�� 1�� �ٲ���
			if(IsPrime(*p,10)) break; //p�� �Ҽ��̸� ���߰� �ƴ� ��� p�� �ٽ� ������
		}
		while(1){
			*q = rand();
			*q = *q|(1<<15); //q�� 16��° bit�� 1�� �ٲ���
			if(IsPrime(*q,10)) break; //p�� �Ҽ��̸� ���߰� �ƴ� ��� p�� �ٽ� ������
		}
		*n = (*p)*(*q);
		if(*n & (1<<31)) break; //n�� 2^31���� ũ�ų� ������ ���߰� �ƴѰ�� �ٽ� p,q,n�� ������
	}
	//e,d ����
	gcd_n = ((*p)-1)*((*q)-1); //����(n) = (p-1)*(q-1)
	while(1){
		//32bit e ����
		temp = rand(); //e�� ���� 15bit�� �������� ����
		if(randomRange(0,100)<=50) temp = temp|(1<<15); //e�� 32��° bit�� �������� ����
		*e = temp<<16; //������ 16���� bit�� ���� 16bit�� shift��
		temp = rand(); //e�� ���� 15bit�� �������� ����
		if(randomRange(0,100)<=50) temp = temp|(1<<15); //e�� 16��° bit�� �������� ����
		*e = (*e)|temp; //���� 16bit�� ���� 16bit�� ���� e�� ������
		// e���� �˻�
		if(*e>1 && *e<gcd_n && GCD(gcd_n,*e)==1) break; //������ �����ϴ� e�� ������ ���߰� �ƴѰ�� e�� �ٽ� ����
	}
	//e^-1�� d ����
	*d = ModInv(*e,gcd_n); //e�� ������ d�� ������
}

/*
 * @brief     RSA �Ϻ�ȣȭ�� �����ϴ� �Լ�.
 * @param     uint data   : Ű ��.
 * @param     uint key    : Ű ��.
 * @param     uint n      : ��ⷯ n ��.
 * @return    uint result : �Ϻ�ȣȭ�� �����
 * @todo      ���� �ȳ� ������ ���ѻ����� �����Ͽ� �ۼ��Ѵ�.
 */
uint miniRSA(uint data, uint key, uint n) {
	uint result;
	printf("input data : %u\n",data);
	result = ModPow(data,key,n); //���� ����� �ؼ� output�� ����
    printf("output data : %u\n",result);
	return result;
}

int main(int argc, char* argv[]) {

	byte plain_text[4] = {0x12, 0x34, 0x56, 0x78};
    uint plain_data, encrpyted_data, decrpyted_data;
    uint seed = time(NULL);
	int tem;
	int num=10;
	int start,end;
    memcpy(&plain_data, plain_text, 4);

	start = time(NULL);
    // ���� ������ �õ尪 ����
    InitWELLRNG512a(&seed);
	srand(seed);
    // RSA Ű ����
    miniRSAKeygen(&p, &q, &e, &d, &n);
    //p = 54623; q = 62189; e = 3152570619; d = 3028275219; n = 3396949747;
	printf("0. Key generation is Success!\n ");
    printf("p : %u\n q : %u\n e : %u\n d : %u\n N : %u\n", p, q, e, d, n);
	printf(" p : ");showbits(p); //p,q,e,d,n�� 2���� bit�� Ȯ��
	printf(" q : ");showbits(q);
	printf(" e : ");showbits(e);
	printf(" d : ");showbits(d);
	printf(" n : ");showbits(n);
	printf("\n p(%u) * q(%u) = n(%u)\n",p,q,n);
	printf(" e(%u) ^ -1 mod <p-1(%u) * q-1(%u)> = d(%u)\n\n",e,p-1,q-1,ModInv(e,(p-1)*(q-1))); //d�� e�� �������� ����

    // RSA ��ȣȭ �׽�Ʈ
    encrpyted_data = miniRSA(plain_data, e, n);
    printf("1. plain text : %u\n", plain_data);    
    printf("2. encrypted plain text : %u\n\n", encrpyted_data);

    // RSA ��ȣȭ �׽�Ʈ
    decrpyted_data = miniRSA(encrpyted_data, d, n);
    printf("3. cipher text : %u\n", encrpyted_data);
    printf("4. Decrypted plain text : %u\n\n", decrpyted_data);

    // ��� ���
    printf("RSA Decryption: %s\n", (decrpyted_data == plain_data) ? "SUCCESS!" : "FAILURE!");
	end = time(NULL);
	printf("%d sec\n",end-start);
	system("pause");
    return 0;
}