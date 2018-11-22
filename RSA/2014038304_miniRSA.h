/* ***************************************************************************** */
/*
  1. miniRSA��� ����
    ������Ͽ��� miniRSA���� �������̽� �� WELL Random number generator �ڵ尡 ���ԵǾ� �ֽ��ϴ�.
    ������ ���� ������ ���� WELL Random number generator�� ����մϴ�.
    WELL Random number generator�� ���õ� ������ ��ǥ �� �� ���ͳݿ��� ���� ã�� �� ������,
    �ҽ��ڵ� ���� ��ǥ������ ���� ���� �� �ֽ��ϴ�. Ȥ�� �����ִ� �л��� ã�ƺ��� �ٶ��ϴ�.
    
    ��� ����� ������ �����ϴ�.
    mini RSA �˰��� ���� �� WELLRNG512a()�� ���ؼ� ������ �����մϴ�.
    ���� InitWELLRNG512a(uint *seed) �Լ��� ���� ���� ���� �˰����� �õ带 �����մϴ�.
*/
/* Copyright:      Francois Panneton and Pierre L'Ecuyer, University of Montreal */
/*                 Makoto Matsumoto, Hiroshima University                        */
/* Notice:         This code can be used freely for personal, academic,          */
/*                 or non-commercial purposes. For commercial purposes,          */
/*                 please contact P. L'Ecuyer at: lecuyer@iro.UMontreal.ca       */
/* ***************************************************************************** */

// WELL Random number generator ���� ��ũ��
#define W	32
#define R	16
#define P	0
#define M1	13
#define M2	9
#define M3	5

#define MAT0POS(t,v)	(v^(v>>t))
#define MAT0NEG(t,v)	(v^(v<<(-(t))))

#define MAT3NEG(t,v)	(v<<(-(t)))
#define MAT4NEG(t,b,v)	(v^((v<<(-(t)))&b))

#define V0		STATE[state_i]
#define VM1		STATE[(state_i+M1) & 0x0000000fU]
#define VM2		STATE[(state_i+M2) & 0x0000000fU]
#define VM3		STATE[(state_i+M3) & 0x0000000fU]
#define VRm1	STATE[(state_i+15) & 0x0000000fU]
#define VRm2	STATE[(state_i+14) & 0x0000000fU]
#define newV0	STATE[(state_i+15) & 0x0000000fU]
#define newV1	STATE[state_i]
#define newVRm1	STATE[(state_i+14) & 0x0000000fU]

#define FACT	2.32830643653869628906e-10

#define RND_MAX	0x00ffff
#define RND_MIN	0x00b505
#define FALSE	0
#define TRUE	1

// mini RSA ���� Ÿ��
typedef unsigned char bool;
typedef unsigned char byte;
typedef unsigned int  uint;

// WELL Random number generator ���� ���� ����
static unsigned int state_i = 0;
static unsigned int STATE[R];
static unsigned int z0, z1, z2;

// WELL Random number generator ���� �Լ�
void InitWELLRNG512a(uint *init);
double WELLRNG512a(void);

// mini RSA ���� �������̽�(�����ؾ� �մϴ�.)
uint modMul(uint x, uint y, uint mod);
uint modPow(uint base, uint exp, uint mod);
bool isPrime(uint n, uint repeat);
uint gcd(uint a, uint b);
uint modInv(uint a, uint m);
void miniRSAKeygen(uint *p, uint *q, uint *e, uint *d, uint *n);
uint miniRSA(uint data, uint key, uint n);

// ���� ������ ���� �ʱ�ȭ �Լ�
void InitWELLRNG512a(uint *init) {
	int j;
	state_i = 0;
	for (j = 0; j < R; j++) STATE[j] = init[j];
}

// ���� ���� �Լ�
double WELLRNG512a(void) {
	z0	= VRm1;
	z1	= MAT0NEG(-16, V0) ^ MAT0NEG(-15, VM1);
	z2	= MAT0POS(11, VM2);
	newV1 = z1 ^ z2;
	newV0 = MAT0NEG(-2, z0) ^ MAT0NEG(-18, z1) ^ MAT3NEG(-28, z2) ^ MAT4NEG(-5, 0xda442d24U, newV1);
	state_i = (state_i + 15) & 0x0000000fU;
	return ((double) STATE[state_i]) * FACT;
}